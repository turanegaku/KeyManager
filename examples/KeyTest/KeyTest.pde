import keymanager.*;

void setup() {
  Key.setApplet(this);
  size(500, 500);
}

void draw() {
  background(0);
  frame.setTitle(""+frameRate);

  for (int i=0; i<Character.MAX_VALUE; i++) {
    if (Key.pressed(i))println(char(i));
  }
}

