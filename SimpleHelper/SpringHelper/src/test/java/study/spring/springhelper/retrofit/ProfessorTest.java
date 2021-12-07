package study.spring.springhelper.retrofit;

import org.apache.ibatis.session.SqlSession;
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
/** Lombok의 Log4j 객체 */

// import lombok.extern.slf4j.Slf4j;

@Slf4j

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })

@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfessorTest {

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void testA() {

		Professor input = new Professor();

		input.setProfno(9901);

		sqlSession.selectOne("ProfessorMapper.selectItem", input);
	}

	@Test
	public void testB() {

		Professor input = new Professor();
		sqlSession.selectList("ProfessorMapper.selectList", input);
	}

	@Test

	public void testC() {

		Professor input = new Professor();

		input.setName("문현지");
		input.setUserid("mhg0000");
		input.setHiredate("2021-01-23");
		input.setSal(500);
		input.setComm(50);
		input.setDeptno(101);
		input.setPosition("교수");
		sqlSession.insert("ProfessorMapper.insertItem", input);

	}

	@Test

	public void testD() {

		Professor input = new Professor();

		input.setProfno(9910);

		sqlSession.delete("ProfessorMapper.deleteItem", input);
	}

	@Test
	public void testE() {
		Professor input = new Professor();

		input.setProfno(9901);
		input.setName("문현지");
		input.setUserid("mhg00000");
		input.setHiredate("2021-01-23");
		input.setSal(500);
		input.setComm(50);
		input.setDeptno(101);
		input.setPosition("교수");
		sqlSession.update("ProfessorMapper.updateItem", input);

	}

	@Test

	public void testF() {

		int count = sqlSession.selectOne("ProfessorMapper.selectCountAll", null);

		log.debug("전체 데이터 수: " + count);

	}
}
