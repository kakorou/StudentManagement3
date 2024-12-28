package raisetech.StudentManagement3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title="受講生管理システム"))
@SpringBootApplication

public class StudentManagement3Application {


	public static void main(String[] args) {
		SpringApplication.run(StudentManagement3Application.class, args);
	}
}
