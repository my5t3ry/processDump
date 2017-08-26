// July 2009
// http://www.abandonedart.org
// http://www.zenbullets.com
//
// This code is more Sean McCulloughs than my own, so
// it would be rude of me to CC license it.
// 
// Credit has to got to Sean 
// http://www.cricketschirping.com/weblog/?p=1047


void mousePressed() {
  background(255);
}

int _num = 60;
int _col = 0;
ArrayList _circles;
long iterationCounter = 0;

void setup() {
  size(500, 300);
  smooth();
  noFill();
  strokeWeight(1);
  frameRate(12);
  createRandomCircles(3);
  background(255);
}

void draw() {
  if (_circles.size() < _num) {
    createNewCircle();
  } else {
    _col = int(random(255));
    createRandomCircles(3);
  }
  for (int i=0; i<_circles.size(); i++) {
    Circle thisCirc = getCircle(i);
    noFill();
    stroke(_col, 5);
    for (int j=0; j<_circles.size(); j++) {
      Circle circ2 = getCircle(j);
      line(thisCirc.x, thisCirc.y, circ2.x, circ2.y);
    } 
  } 
  
  for (int i=1; i<50; i++) {
    iterateLayout(i);
  }
}

Comparator comp = new Comparator() {
    public int compare(Object p1, Object p2) {
      Circle a = (Circle)p1;
      Circle b = (Circle)p2;
      if (a.distanceToCenter() < b.distanceToCenter()) 
        return 1;
      else if (a.distanceToCenter() > b.distanceToCenter())
        return -1;
      else
        return 0;
    }
};

void iterateLayout(int iterationCounter) {

  Object circs[] = _circles.toArray();
  Arrays.sort(circs, comp);

  //fix overlaps
  Circle ci, cj;
  PVector v = new PVector();

  for (int i=0; i<circs.length; i++) {
    ci = (Circle)circs[i];
    for (int j=i+1; j<circs.length; j++) {
      if (i != j) {
        cj = (Circle)circs[j];
        float dx = cj.x - ci.x;
        float dy = cj.y - ci.y;
        float r = ci.radius + cj.radius;
        float d = (dx*dx) + (dy*dy);
        if (d < (r * r) - 0.01 ) {

          v.x = dx;
          v.y = dy;

          v.normalize();
          v.mult((r-sqrt(d))*0.5);

          cj.x += v.x;
          cj.y += v.y;
          ci.x -= v.x;
          ci.y -= v.y; 
        }
      }
    }
  }

  //Contract
  float damping = 0.1/(float)(iterationCounter);
  for (int i=0; i<circs.length; i++) {
    Circle c = (Circle)circs[i];
    v.x = c.x-width/2;
    v.y = c.y-height/2;
    v.mult(damping);
    c.x -= v.x;
    c.y -= v.y;
  }
}





void createRandomCircles(int n) {
  _circles = new ArrayList();
  colorMode(HSB, 255);
  while (n-- > 0) {
    createNewCircle();
  }
  colorMode(RGB,255);
}

void createNewCircle() {
    Circle c = new Circle(random(width * 2) - (width/2), random(height * 2) - (height/2), random(_num)+10);
    c.myColor = color(random(255), 128, 200, 128);
    _circles.add(c);
}

Circle getCircle(int i) {
  return (Circle)_circles.get(i);
}




class Circle {
  public float x, y, radius;
  public color myColor;
  
  public Circle(float x, float y, float radius) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    myColor = color(64,64,64,64);
  }
 
 public boolean contains(float x, float y) {
   float dx = this.x - x;
   float dy = this.y - y;
   return sqrt(dx*dx + dy*dy) <= radius;
 }
  
  public float distanceToCenter() {
    float dx = x - WIDTH/2;
    float dy = y - HEIGHT/2;
    return (sqrt(dx*dx + dy*dy));
  } 
  
  public boolean intersects(Circle c) {
    float dx = c.x - x;
    float dy = c.y - y;
    float d = sqrt(dx*dx + dy*dy);
    return d < radius || d < c.radius;
  }
}

