package raisetech.StudentManagement3.Controller;

import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement3.domain.StudentDetail;
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

 @GetMapping("/studentList")
public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生の情報を取得します
   * @param id　受講生ID
   * @return　受講生
   */

  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable  String id) {
    return service.searchStudent(id);
  }

  /**
   *
   * @param studentDetail
   * @return
   */

  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody  StudentDetail studentDetail) {
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
      public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail){
      service.updateStudent(studentDetail);
      return ResponseEntity.ok("更新処理が成功しました");
    }
  }



