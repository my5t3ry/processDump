import processing.core.PApplet;

/**
 * created by: sascha.bast
 * since: 26.08.17
 */
public class Flies extends PApplet {

    public static void main(String... args) {
        Flies main = new Flies();
        PApplet.runSketch(new String[]{"Main"}, main);
    }

    @Override
    public void setup() {
        fill(0, 32);
        noiseDetail(6, 0.5f);
    }


    @Override
    public void settings() {
        super.settings();
        size(640, 640);

    }

    public void draw() {
        noStroke();
        rect(0, 0, width, height);

        stroke(255, 127);
        for (int n = 0; n < 1000; n++) {
            float x = noise(n / 5.0f, n / 11.0f, frameCount / 300.0f);
            float y = noise(n / 13.0f, n / 7.0f, frameCount / 300.0f);
            point(x * width, y * height);
        }
    }

}
