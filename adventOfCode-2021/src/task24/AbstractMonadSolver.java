package task24;

import java.util.List;
import java.util.Objects;

abstract class AbstractMonadSolver implements MonadSolver {
  protected List<Instruction> ins_list;
  
  @Override
  public void setInstructions(List<Instruction> ins_list) {
    this.ins_list = Objects.requireNonNull(ins_list);
  }
}
