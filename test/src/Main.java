import processing.core.PApplet;

/**
 * created by: sascha.bast
 * since: 26.08.17
 */
public class Main extends PApplet {

    private Orbit one[];
    private int range = 60;

    public static void main(String... args) {
        Main main = new Main();
        PApplet.runSketch(new String[]{"Main"}, main);
    }

    @Override
    public void settings() {
        super.settings();
        size(680, 360);
        smooth();
        init();
    }

    public void draw() {
        noStroke();
        noCursor();
        fill(255, 20);
        for (int i = 0; i < range; i++) {
            if (i != 0) {
                one[i].update(mouseX, mouseY);
                one[i].display();
            }
        }
    }

    private void init() {
        one = new Orbit[range];
        for (int i = 0; i < range; i++) {
            one[i] = new Orbit(i);
        }
    }

    class Orbit {
        int curX;
        int curY;
        float radius;

        float t;
        float c;
        float x;
        float y;

        boolean limit;
        float velRadial;
        float velAng;
        float posRadial;

        float min;
        float max;

        float id;

        Orbit(int _id) {
            curX = width / 2;
            curY = height / 2;

            limit = true;
            velAng = random(-0.01f, 0.01f);
            velRadial = random(-0.5f, 0.5f);
            posRadial = random(-PI, PI);
            min = 3;
            max = 400;
            radius = random(min, max);
            id = _id;
            c = 20;
            t = random(1, 4);
        }

        void update(int curX, int curY) {
            posRadial += velAng;
            if (limit) {
                radius += velRadial;
            } else {
                radius -= velRadial;
            }
            if (radius > max) {
                limit = false;
            }
            if (radius < min) {
                limit = true;
            }
            x = curX + (radius * sin(posRadial));
            y = curY + (radius * cos(posRadial));
        }

        void display() {
            noStroke();
            fill(c, 30);
            ellipse(x, y, t, t);
            noStroke();
        }
    }
}
