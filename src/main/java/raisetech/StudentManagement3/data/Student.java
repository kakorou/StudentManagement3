package raisetech.StudentManagement3.data;

import lombok.Getter;
import lombok.Setter;

  @Getter
  @Setter
  public class Student {

    private String id;
    private String name;
    private String kanaName;
    private String nickname;
    private String email;
    private String area;
    private int age;
    private String sex;
    //備考欄
    private String remark;
    //解約した場合に削除する、検索データに含めないようにする論理削除
    private boolean isDeleted;

  }

