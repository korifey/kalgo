package task24;

class Mul extends Instruction {
  public Mul(Variable v, long value) {
    super(Type.mul);
    setFunction(s -> s.getAndSet(v, s.get(v) * value));
    setAsm(createBinopVarValueAsm(v, value));
  }

  public Mul(Variable v1, Variable v2) {
    super(Type.mul);
    setFunction(s -> s.getAndSet(v1, s.get(v1) * s.get(v2)));
    setAsm(createBinopVarVarAsm(v1, v2));
  }
}
