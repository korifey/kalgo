package task24;

import java.util.List;
import java.util.Objects;

class Monad {
  public static long getMonadOutput(long digits, List<Instruction> ins_list) {
    State s = new State(digits);
    for (Instruction ins : ins_list) {
      ins.go(s);
    }
    
    return s.z();
  }
  
  public static void runInstructions(State s, List<Instruction> ins_list) {
    for (Instruction ins : ins_list) {
      ins.go(s);
    }
  }
  
  public static void printInstructions(List<Instruction> ins_list) {
    for (Instruction ins : ins_list) {
      System.out.println(ins);
    }
  }
  
  private final List<Instruction> ins_list;
  private MonadSolver solver; // Strategy
  
  public Monad(List<Instruction> ins_list) {
    this.ins_list = ins_list;
    setMonadSolver(new DpMonadSolver()); // default
  }
  
  public final void setMonadSolver(MonadSolver solver) {
    Objects.requireNonNull(solver);
    solver.setInstructions(ins_list);
    
    this.solver = solver;
  }
  
  public long findHighest() {
    return solver.findHighest();
  }
  
  public long findLowest() {
    return solver.findLowest();
  }
  
  public void printInstructions() {
    Monad.printInstructions(ins_list);
  }
}
