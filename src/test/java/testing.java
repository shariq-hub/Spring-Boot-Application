import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DemoEntity.TeacherEntity;
import com.example.demo.demoRepo.TeacherEntityRepo;

@ExtendWith(MockitoExtension.class)

public class testing {
	
	@Autowired
	TeacherEntityRepo teacherEntityRepo;
	
	@Test
	public void test() {
		TeacherEntity teacherEntity=new TeacherEntity();
		teacherEntity.setName("teacher1");
		teacherEntity.setCell("+923232345566");
		teacherEntity.setEmail("teacher1@gmail.com");
		teacherEntity.setId(101);
		teacherEntityRepo.save(teacherEntity);
		
		 String actualResult = teacherEntityRepo.findById(101).get().getName();
	        //assertThat(actualResult).isTrue();
		 assertEquals("teacher1", actualResult);
		
	}
	
	

}
