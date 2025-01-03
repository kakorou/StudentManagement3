package raisetech.StudentManagement3.service;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement3.Controller.Converter.StudentConverter;
import raisetech.StudentManagement3.data.Student;
import raisetech.StudentManagement3.data.StudentCourse;
import raisetech.StudentManagement3.domain.StudentDetail;
import raisetech.StudentManagement3.exception.IdNotFoundException;
import raisetech.StudentManagement3.repository.StudentRepository;

/**
 * サービスは受講生情報の呼び出しと、コンバーターの呼び出しを行う（メモ）
 * 受講生情報を取り扱うサービスです
 * 受講生の検索や登録、更新処理を行います
 */

@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository,StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細一覧の検索を行います
   * 全件検索を行うので条件指定は行いません
   * @return　受講生一覧（全件）
   *
   */
  public List<StudentDetail> searchStudentList() {
    List<Student>studentList =repository.search();
    List<StudentCourse> studentCourseList =repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生詳細検索です
   * IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コースを取得して設定します
   * @param id 受講生id
   * @return　受講生詳細
   */


  public StudentDetail searchStudent(String id) throws IdNotFoundException {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());

    if (student == null) {
      throw new IdNotFoundException("IDが見つかりませんでした");
    }
    return new StudentDetail(student, studentCourse);

  }

  /**
   * 受講生詳細の登録を行います
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値やコース開始日、コース終了日を設定します
   * 
   * @param studentDetail　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    //準備
    Student student = studentDetail.getStudent();
    //やりたい処理
    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourse -> {
      initStudentCourse(studentsCourse, student);
      repository.registerStudentCourse(studentsCourse);
    });
    //結果
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する
   *
   * @param studentCourse　受講生コース情報
   * @param student　受講生
   */

  private void initStudentCourse(StudentCourse studentCourse, Student student) {

    LocalDateTime now = LocalDateTime.now();

    studentCourse.setStudentId(student.getId());
    studentCourse.setCourseStartAt(now);
    studentCourse.setCourseEndAt(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います
   * 受講生の情報と受講生コース情報をそれぞれ更新します
   * @param studentDetail　受講生詳細
   */

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
  }
}
