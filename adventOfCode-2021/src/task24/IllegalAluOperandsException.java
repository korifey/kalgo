package task24;

public class IllegalAluOperandsException extends IllegalArgumentException {
  public IllegalAluOperandsException() {
    super();
  }

  public IllegalAluOperandsException(String s) {
    super(s);
  }

  public IllegalAluOperandsException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalAluOperandsException(Throwable cause) {
    super(cause);
  }
}
