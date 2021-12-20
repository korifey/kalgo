package task17;

class Input {
  private final int x1, x2;
  private final int y1, y2;
  
  public Input(int x1, int x2, int y1, int y2) {
    super();
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }
  
  public void print() {
    System.out.printf("x[%d..%d], y[%d..%d]%n", x1, x2, y1, y2);
  }

  public int getX1() {
    return x1;
  }

  public int getX2() {
    return x2;
  }

  public int getY1() {
    return y1;
  }

  public int getY2() {
    return y2;
  }
}
