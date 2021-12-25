package task24;

import java.util.ArrayList;
import java.util.List;

class Monad {
  private final List<Instruction> il = new ArrayList<>();
  
  public Monad() {
    
  }
  
  public void findHighest() {
    int digs[] = new int[14];
    for (int i = 0; i < 14; ++i) {
      digs[i] = 9;
    }

    long k = 0;
    while (k++ < 1_000_000) { // Generates 14-digits numbers without 0s
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
      
      long z = getMonadOutput(num);
      if (z == 0) {
        System.out.println(num);
        break;
      } else {
        System.out.println("NO: d=" + num + " z=" + z);
      }
    }
  }

  public long getMonadOutput(long digits) {
    State s = new State(digits);
    for (Instruction ins : il) {
      ins.go(s);
    }
    
    return s.z();
  }
  
  public void add(Instruction ins) {
    this.il.add(ins);
  }
  
  public void print() {
    for (Instruction ins : il) {
      System.out.println(ins);
    }
  }
}
