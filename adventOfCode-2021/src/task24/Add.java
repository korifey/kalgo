package task24;

class Add extends Instruction {
  public Add(Variable v, long value) {
    super(Type.add);
    setFunction(s -> s.getAndSet(v, s.get(v) + value));
    setAsm(createBinopVarValueAsm(v, value));
  }

  public Add(Variable v1, Variable v2) {
    super(Type.add);
    setFunction(s -> s.getAndSet(v1, s.get(v1) + s.get(v2)));
    setAsm(createBinopVarVarAsm(v1, v2));
  }
}
