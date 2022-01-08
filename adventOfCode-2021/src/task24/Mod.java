package task24;

class Mod extends Instruction {
  public Mod(Variable v, long value) {
    super(Type.mod);
    setFunction(s -> s.getAndSet(v, validateA(s.get(v)) % validateB(value)));
    setAsm(createBinopVarValueAsm(v, value));
  }

  public Mod(Variable v1, Variable v2) {
    super(Type.mod);
    setFunction(s -> s.getAndSet(v1, validateA(s.get(v1)) % validateB(s.get(v2))));
    setAsm(createBinopVarVarAsm(v1, v2));
  }

  private long validateA(long value) {
    if (value < 0)
      throw new IllegalAluOperandsException("mod a b: a<0");
    
    return value;
  }
  
  private long validateB(long value) {
    if (value <= 0)
      throw new IllegalAluOperandsException("mod a b: b<=0");
    
    return value;
  }
}
