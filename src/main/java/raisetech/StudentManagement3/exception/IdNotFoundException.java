package raisetech.StudentManagement3.exception;

public class IdNotFoundException extends Exception{

  public IdNotFoundException() {
    super();
  }

  public IdNotFoundException(String message) {
    super("IDが見つかりませんでした");
  }

  public IdNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public IdNotFoundException(Throwable cause) {
    super(cause);
  }

  protected IdNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
