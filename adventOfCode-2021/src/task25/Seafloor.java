package task25;

import java.util.List;

class Seafloor {
  public static final int E = 0; // empty: .
  public static final int R = 1; // right: >
  public static final int D = 2; // down: V

  private final int X, Y;
  private int[][] a, b; // a - current, b - old

  public Seafloor(List<List<Character>> input) {
    Y = input.size();
    X = input.get(0).size();

    a = new int[Y][];
    b = new int[Y][];
    for (int y = 0; y < Y; ++y) {
      a[y] = new int[X];
      b[y] = new int[X];
      for (int x = 0; x < X; ++x) {
        char c = input.get(y).get(x);
        int val;
        switch (c) {
          case '.':
            val = E;
            break;
          case '>':
            val = R;
            break;
          case 'v':
            val = D;
            break;
          default:
            throw new IllegalArgumentException("Bad input syntax. Unknown symbol '" + c + "'");
        }

        a[y][x] = val;
        b[y][x] = val;
      }
    }
  }
  
  public void print() {
    for (int y = 0; y < Y; ++y) {
      for (int x = 0; x < X; ++x) {
        char c = '?';
        switch (a[y][x]) {
          case E:
            c = '.';
            break;
          case R:
            c = '>';
            break;
          case D:
            c = 'v';
            break;
        }

        System.out.print(c);
      }
      System.out.println();
    }
  }
  
  private void update() {
    for (int y = 0; y < Y; ++y) {
      for (int x = 0; x < X; ++x) {
        a[y][x] = b[y][x];
      }
    }
  }
  
  public boolean tick() {
    int nMoved = 0;
    for (int y = 0; y < Y; ++y) {
      for (int x = 0; x < X; ++x) {
        nMoved += moveR(x, y);
      }
    }
    
    update();
    
    for (int y = 0; y < Y; ++y) {
      for (int x = 0; x < X; ++x) {
        nMoved += moveD(x, y);
      }
    }
    
    update();
    
    return nMoved > 0;
  }
  
  private int moveR(int x, int y) {
    if (a[y][x] != R) {
      return 0;
    }
    
    int x1 = (x + 1) % X;
    if (a[y][x1] != E)
      return 0;
    else {
      b[y][x] = E;
      b[y][x1] = R;
      return 1;
    }
  }
  
  private int moveD(int x, int y) {
    if (a[y][x] != D)
      return 0;
    
    int y1 = (y + 1) % Y;
    if (a[y1][x] != E)
      return 0;
    else {
      b[y][x] = E;
      b[y1][x] = D;
      return 1;
    }
    
  }
  
}
