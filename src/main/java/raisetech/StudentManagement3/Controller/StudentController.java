package raisetech.StudentManagement3.Controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement3.domain.StudentDetail;
import raisetech.StudentManagement3.exception.TestException;
import raisetech.StudentManagement3.service.StudentService;

/**
 *受講生の検索や、更新などを行うREST　APIとして実行されるControllerです
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

    @Autowired
      public StudentController(StudentService service) {
      this.service = service;
  }

  /**
   * 受講生詳細一覧検索です
   * 全件検索をおこなうので、指定は行うものになります
   *
   * @return 受講生詳細一覧全件検索
   */
    @Operation(summary = "一覧検索",description = "受講生の一覧検索をします")
    @GetMapping("/studentList")
      public List<StudentDetail> getStudentList() throws TestException  {
      try {//受講生を検索
          return service.searchStudentList();
        }catch(Exception e){
          //何らかのエラーが発生した際にTestExceptionをスロー
          throw new TestException("受講生を検索できませんでした",e);
        }
  }

  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生の情報を取得します
   * @param id　受講生ID
   * @return　受講生
   */
    @GetMapping("/student/{id}")
      public StudentDetail getStudent(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {
      return service.searchStudent(id);
  }

  /**
   *
   * @param studentDetail
   * @return
   */
    @Operation(summary = "受講生登録",description = "受講生を登録します")
    @PostMapping("/registerStudent")
      public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid  StudentDetail studentDetail) {
      StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
      return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います
   * キャンセルフラグの更新もここで行います（論理削除
   * @param studentDetail 　受講生詳細
   * @return 実行結果
   */
    @PutMapping ("/updateStudent")
      public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail){
      service.updateStudent(studentDetail);
      return ResponseEntity.ok("更新処理が成功しました");
    }

}



