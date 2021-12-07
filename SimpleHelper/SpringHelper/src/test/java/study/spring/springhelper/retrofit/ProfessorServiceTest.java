package study.spring.springhelper.retrofit;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.Professor;
import study.spring.springhelper.service.ProfessorService;

/** Lombok의 Log4j 객체 */

// import lombok.extern.slf4j.Slf4j;

@Slf4j

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })

@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfessorServiceTest {

	
	@Autowired
	private ProfessorService professorService;

	
	@Test
	public void testA() {
		// 검색조건으로 사용될 POJO 클래스 객체
		Professor input = new Professor();
		input.setProfno(9901);
		Professor output = null;
		try {
			output = professorService.getProfessorItem(input);
			log.debug(output.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testB() {
		
		Professor input = new Professor();
		List<Professor> output = null;
		try {
			output = professorService.getProfessorList(input);
			for (Professor item : output) {
				log.debug(item.toString());
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testC() {
		int count = 0;
		try {
			count = professorService.getProfessorCount(null);
			log.debug("전체 데이터 수: " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testD() {
		int count = 0;
		Professor input = new Professor();
		input.setName("김도훈");
		try {
			count = professorService.getProfessorCount(input);
			log.debug("검색된 데이터 수: " + count);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testE() {
		Professor input = new Professor();
		input.setName("이광호");
		input.setUserid("leekh4232");
		input.setHiredate("2021-01-23");
		input.setSal(500);
		input.setComm(50);
		input.setDeptno(101);
		input.setPosition("교수");
		int output = 0;
		try {
			output = professorService.addProfessor(input);
			log.debug("저장된 데이터 수: " + output);
			// [중요] 생성된 PK값은 MyBatis에 의해 입력 파라미터의 해당 멤버변수log.debug("생성된 PK값 : "+
			// input.getDeptno());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testF() {
		Professor input = new Professor();
		input.setProfno(9901);
		input.setName("이광호");
		input.setUserid("leekh4232");
		input.setHiredate("2021-01-23");
		input.setSal(500);
		input.setComm(50);
		input.setDeptno(101);
		input.setPosition("교수");
		int output = 0;
		try {
			output = professorService.editProfessor(input);
			log.debug("수정된 데이터 수: " + output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	
	@Test
	public void testG() {
		Professor input = new Professor();
		input.setDeptno(9991);
		int output = 0;
		try {
			output = professorService.deleteProfessor(input);
			log.debug("삭제된 데이터 수: " + output);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
