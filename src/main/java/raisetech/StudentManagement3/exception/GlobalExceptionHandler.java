package raisetech.StudentManagement3.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

//  @ExceptionHandler(TestException.class)
//  public ResponseEntity<String>handleTestException(TestException ex){
//    //HTTP  500とともにエラーメッセージを返す
//   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//  }
}
