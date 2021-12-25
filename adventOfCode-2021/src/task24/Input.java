package task24;

public class Input extends Instruction {
  public Input(Variable v) {
    super(Type.inp);
    setFunction(s -> s.getAndSet(v, s.extractDigit()));
    setAsm(String.format("%s %s", getType(), v));
  }
}
