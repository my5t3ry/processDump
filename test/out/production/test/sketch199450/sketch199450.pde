void setup()
{
  size(640,640);
  fill(0,32);

  noiseDetail(6, 0.5);
}

void draw()                                            
{
  noStroke();
  rect(0,0,width,height);

  stroke(255,127);
  for(int n=0; n<1000; n++) {
    float x = noise(n/5.0, n/11.0, frameCount/300.0);
    float y = noise(n/13.0, n/7.0, frameCount/300.0);
    point( x*width, y*height );
  }
}
