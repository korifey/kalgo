package task24;

import java.util.List;

// Strategy
interface MonadSolver {
  public long findHighest();
  public void setInstructions(List<Instruction> ins_list);
}
