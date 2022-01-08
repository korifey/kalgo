package task24;

class BruteForceMonadSolver extends AbstractMonadSolver {

  @Override
  public long findHighest() {
    final long whenToStop = 1_000_000; // Otherwise 9^14

    int digs[] = new int[14];
    for (int i = 0; i < 14; ++i) {
      digs[i] = 9;
    }

    long k = 0;
    while (k++ < whenToStop) { // Generates 14-digits numbers without 0s
      long num = 0;
      long pow = 1;
      boolean carry = true;
      for (int i = 13; i >= 0; --i) {
        num += digs[i] * pow;
        pow *= 10;

        if (carry == true) {
          --digs[i];
          carry = false;
        }

        if (digs[i] == 0) {
          carry = true;
          digs[i] = 9;
        }
      }

      long z = Monad.getMonadOutput(num, ins_list);
      if (z == 0) {
        System.out.println(num);
        break;
      } else {
        System.out.println("NO: d=" + num + " z=" + z);
      }
    }

    return Long.MIN_VALUE; // stub
  }
}
