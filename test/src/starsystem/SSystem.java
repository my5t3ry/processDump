package starsystem;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * created by: sascha.bast
 * since: 26.08.17
 */
public class SSystem extends PApplet {

    PImage earth;
    PImage moon;
    PImage sun;
    PImage starbg;
    PImage starfr;
    PImage glare;
    PImage starwars;
    int amp = 200;
    float diX;
    float diY;
    float i;
    PFont font;


    public static void main(String... args) {
        SSystem main = new SSystem();
        PApplet.runSketch(new String[]{"Main"}, main);
    }

    @Override
    public void setup(){
      smooth();
      earth = loadImage("earth.png");
      moon = loadImage("moon.png");
      sun = loadImage("sun.png");
      starbg = loadImage("starbg.jpg");
      starfr = loadImage("starfr.png");
      glare = loadImage("glare.png");
      starwars = loadImage("starwar.gif");
      i = 0;
      font = loadFont("EurostileRegular-16.vlw");

    }

    @Override
    public void settings() {
        super.settings();
        size(500, 500,P2D);

    }

    public void draw() {



      diX = width/2- mouseX;//distance from mouse to center
      diY = height/2 - mouseY;

      imageMode(CENTER);

      background(0);

      float starbgX = width/2;
      float starbgY = height/2;

      starbgX = starbgX - diX*0.6f;
      starbgY = starbgY - diY*0.6f;

      image(starbg, starbgX, starbgY, width*2, height*2);
      image(starfr, starbgX*1.2f, starbgY*1.2f, width*1.5f, height*1.5f);



      float sunX = 0;
      float sunY = 0;

      if (mouseX > width)  {
        sunX = sunX + diX*0.1f;
      } else {
        sunX = sunX - diX*0.1f;}

      if (mouseY > height)  {
        sunY = sunY + diY*0.1f;
      } else {
        sunY = sunY - diY*0.1f;}

      if (i < 360) {
        i = i + 0.1f ;} else {
          i = 0;}


      pushMatrix();
      imageMode(CENTER);
      translate(width/2, height/2);
      rotate(i*0.01f);
      image(sun, sunX, sunY, 150, 150);
      popMatrix();








      float time =  millis()*0.0005f;
      float earthX = cos(time) * amp + width/2;
      float earthY = sin(time) * amp + height/2;




      pushMatrix();

      image(earth, earthX - diX*0.15f, earthY - diY*0.15f, 50, 50);
      popMatrix();

      float moonX = cos(time*10) * amp/5 + earthX - diX*0.15f;
      float moonY = sin(time*10) * amp/5 + earthY - diY*0.15f;

      image(moon, moonX , moonY, 20, 20);


      float starfrX = width/2;
      float starfrY = height/2;



      starfrX = starfrX + diX*2;
      starfrY = starfrY + diY*2;

       blendMode(ADD);

      image(starfr, starfrX, starfrY, width*4, height*4);
      image(starfr, starfrX*1.2f, starfrY*1.2f, width*16f, height*16f);

      pushMatrix();
      textSize(13);
      int i = 0;
      while (i < 10){
        fill(0x3411FA);
          text("Once in a galaxy far, far away...", width/2, 8 * height/10 + i);
          i = i +1;}


      fill(0x3411FA);
      text("Once in a galaxy far, far away...", width/2, 8 * height/10);
      fill(0xffffff);
      text("Once in a galaxy far, far away...", width/2, 8 * height/10+2);
      popMatrix();

       if (frameCount%500 > 250) {
        float clearc;
        if (keyPressed == true) {
          clearc = 0;} else {
            clearc = 99999; }

        blendMode(NORMAL);
        image(starwars, width/2 + clearc, height/2 + clearc);





      }

           blendMode(ADD);
        image(starfr, starfrX*1.2f, starfrY*1.2f, width*16, height*16);

        blendMode(NORMAL);


    }



}
