package raisetech.StudentManagement3.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.EnumOptions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import raisetech.StudentManagement3.Controller.Converter.StudentConverter;
import raisetech.StudentManagement3.data.Student;
import raisetech.StudentManagement3.data.StudentCourse;
import raisetech.StudentManagement3.domain.StudentDetail;
import raisetech.StudentManagement3.repository.StudentRepository;

//Mockito…インスタンスを作ってくれる、中身がないものを作る
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before(){
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の全件検索_リポジトリとコンバーターの処理が適切に呼び出せていること(){
    //事前準備
    StudentService sut = new StudentService(repository,converter);
    List<Student>studentList = new ArrayList<>();
    List<StudentCourse>studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    List<StudentDetail> actual = sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList,studentCourseList);
  }
}

//課題
//searchStudent,registerStudent,initStudentCourse(できれば),update のテストクラスを実装する
