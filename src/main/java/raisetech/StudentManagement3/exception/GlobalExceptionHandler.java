package raisetech.StudentManagement3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  //例外をキャッチしてポストマンに適切なメッセージを返すためのクラス
  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<String> handleIDNotFoundException(IdNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}

