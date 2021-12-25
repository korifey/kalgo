package task24;

import java.util.function.Consumer;

abstract class Instruction {
  public enum Type {
    inp,
    add,
    mul,
    div,
    mod,
    eql
  }

  private final Type type;
  private Consumer<State> f;
  private String asm;
  
  public Instruction(Type type) {
    this.type = type;
  }

  public void go(State s) {
    f.accept(s);
  }

  public Type getType() {
    return type;
  }
  
  @Override
  public String toString() {
    return asm;
  }
  
  protected final void setAsm(String asm) {
    this.asm = asm;
  }
  
  protected final void setFunction(Consumer<State> f) {
    this.f = f;
  }
  
  protected final String createBinopVarValueAsm(Variable v, long value) {
    return String.format("%s %s %d", getType(), v, value);
  }
  
  protected final String createBinopVarVarAsm(Variable v1, Variable v2) {
    return String.format("%s %s %s", getType(), v1, v2);
  }
}
