package task21;

public class DiracGame {
  int p1, p2;

  public DiracGame(int p1, int p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public void play() {
    Pair p = aux(p1 - 1, p2 - 1, 0, 0, true, 1);
    System.out.println(Math.max(p.a, p.b));
  }

  static final int[] vals = {3, 4, 5, 6, 7, 8, 9};
  static final long[] vars = {1, 3, 6, 7, 6, 3, 1};

  Pair aux(int p1, int p2, int score1, int score2, boolean isP1, long var) {
    Pair p = new Pair();
    if (isP1) { // Player 1
      if (score2 >= 21) {
        p.add(0L, var);
      } else {
        for (int i = 0; i < vals.length; ++i) {
          int x = go(p1, vals[i]);
          p.add(aux(x, p2, score1 + x + 1, score2, false, vars[i]));
        }

        p.mult(var);
      }
    } else { // Player 2
      if (score1 >= 21) {
        p.add(var, 0L);
      } else {
        for (int i = 0; i < vals.length; ++i) {
          int x = go(p2, vals[i]);
          p.add(aux(p1, x, score1, score2 + x + 1, true, vars[i]));
        }

        p.mult(var);
      }
    }

    return p;
  }

  int go(int score, int roll) {
    return (score + roll) % 10;
  }
}
