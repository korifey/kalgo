package task24;

class Div extends Instruction {
  public Div(Variable v, long value) {
    super(Type.div);
    setFunction(s -> s.getAndSet(v, s.get(v) / validateB(value)));
    setAsm(createBinopVarValueAsm(v, value));
  }

  public Div(Variable v1, Variable v2) {
    super(Type.div);
    setFunction(s -> s.getAndSet(v1, s.get(v1) / validateB(s.get(v2))));
    setAsm(createBinopVarVarAsm(v1, v2));
  }

  private long validateB(long value) {
    if (value == 0)
      throw new IllegalArgumentException("div a b: b=0");
    
    return value;
  }
}
