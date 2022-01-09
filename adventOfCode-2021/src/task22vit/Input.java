package task22vit;

class Input {
  private final boolean on;
  private final Cube cube;

  public Input(boolean on, Cube cube) {
    this.on = on;
    this.cube = cube;
  }
  
  public boolean isOn() {
    return on;
  }

  public Cube cube() {
    return cube;
  }
  
  @Override
  public String toString() {
    return String.format("%s %s", on ? "on" : "off", cube);
  }
}
