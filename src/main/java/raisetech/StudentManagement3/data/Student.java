package raisetech.StudentManagement3.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

//課題　api仕様書を完成させる
@Schema(description = "受講生情報")
  @Getter
  @Setter
  public class Student {


    @Pattern(regexp = "^\\d+$")
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String kanaName;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String area;

    private int age;

    @NotBlank
    private String sex;

    //備考欄
    private String remark;

    //解約した場合に削除する、検索データに含めないようにする論理削除
    private boolean isDeleted;

  }

