package task17;

import static java.lang.Math.*; 

class ProbeLauncher {
  private final int x1, x2, y1, y2;
  private final int h;
  
  public ProbeLauncher(Input input) {
    x1 = input.getX1();
    x2 = input.getX2();
    y1 = input.getY1();
    y2 = input.getY2();
    h = y2 - y1;
  }
  
  public static class HighestYResult {
    final int y, vx, vy;

    public HighestYResult(int y, int vx, int vy) {
      this.y = y;
      this.vx = vx;
      this.vy = vy;
    }

    public int getY() {
      return y;
    }

    public int getVx() {
      return vx;
    }

    public int getVy() {
      return vy;
    }
    
    @Override
    public String toString() {
      return String.format("y=%d (vx, vy)=(%d, %d)", y, vx, vy);
    }
  }
  
  private int distinct; // gets reevaluated every time findHighestY() is called

  /**
   * (Task 1) Finds the highest Y that can be reached within an allowed set of initial (vx, vy).
   * 
   * @return result object
   */
  public HighestYResult findHighestY() {
    HighestYResult res = new HighestYResult(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    int distinct = 0;
    
    for (int vx0 = 0; vx0 <= x2; ++vx0) {
      for (int vy0 = min(-2 * h, -max(abs(y1), abs(y2))); vy0 <= max(2 * h, max(abs(y1), abs(y2))); ++vy0) {
        int y = evalHeight(vx0, vy0);
        
        if (y > res.getY()) {
          res = new HighestYResult(y, vx0, vy0);
        }
        
        if (y > Integer.MIN_VALUE) {
          distinct++;
        }
      }
    }
    
    this.distinct = distinct;
    
    return res;
  }
  
  /**
   * (Task 2) Returns the number of distinct valid pairs (vx, vy). Call to {@link #findHighestY()} is required first.
   * 
   * @return how many distinct combinations of (vx, vy) eventually land the probe in target
   * @see #findHighestY
   */
  public int getDistinct() {
    return distinct;
  }
  
  private boolean inTarget(int x, int y) {
    return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2;
  }
  
  private int evalHeight(int vx, int vy) {
    int highestY = Integer.MIN_VALUE;

    int x = 0;
    int y = 0;
    
    do {
      x += vx;
      y += vy;
      
      if (y > highestY) {
        highestY = y;
      }
      
      if (inTarget(x, y)) {
        return highestY;
      }
      
      vx += vx > 0 ? -1 : 0;
      vy += -1;
    } while (
                     (isIn(x, x1, x2) || isIn(x + vx, x1, x2) || isApproaching(x, x + vx, x1, x2)) // x condition
        && (vy >= 0 || isIn(y, y1, y2) || isIn(y + vy, y1, y2) || isApproaching(y, y + vy, y1, y2)) // y condition
    ); 
    
    return Integer.MIN_VALUE;
  }
  
  private boolean isIn(int x, int a, int b) {
    return a <= x && x <= b;
  }
  
  private boolean isApproaching(int before, int after, int a, int b) {
    return dist(before, a, b) >= dist(after, a, b);
  }
  
  private int dist(int x, int a, int b) {
    return min(abs(x - a), abs(x - b));
  }
}
