package task24;

class Eql extends Instruction {
  public Eql(Variable v, long value) {
    super(Type.eql);
    setFunction(s -> s.getAndSet(v, s.get(v) == value ? 1 : 0));
    setAsm(createBinopVarValueAsm(v, value));
  }

  public Eql(Variable v1, Variable v2) {
    super(Type.eql);
    setFunction(s -> s.getAndSet(v1, s.get(v1) == s.get(v2) ? 1 : 0));
    setAsm(createBinopVarVarAsm(v1, v2));
  }
}
