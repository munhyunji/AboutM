package study.spring.springhelper.retrofit;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.ApiSimpleService;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.model.GradeList;
import study.spring.springhelper.model.Phone;
import study.spring.springhelper.model.Simple;

@Slf4j
/** JUnit에 의한 테스트 클래스로 정의 */
// import org.junit.runner.RunWith;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
/** Spring의 설정 파일들을 읽어들이도록 설정 (**은 `모든` 이라는 의미) */
// import org.springframework.test.context.ContextConfiguration;
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
/** 웹 어플리케이션임을 명시 */
// import org.springframework.test.context.web.WebAppConfiguration;
@WebAppConfiguration
/** 메서드 이름순서로 실행하도록 설정 (설정하지 않을 경우 무작위 순서로 실행됨) */
// import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleRetrofitTest {
	
	@Autowired
	RetrofitHelper retrofitHelper;
	
	
	@Test
	public void TestA() {
		Retrofit retrofit =retrofitHelper.getRetrofit(ApiSimpleService.BASE_URL);
		
		ApiSimpleService apiSimpleService = retrofit.create(ApiSimpleService.class);
		
		Call<Simple> call = apiSimpleService.getSimple();
		
		Simple simple = null;
		
		try {
			simple = call.execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug(simple.toString());
		
	}
	@Test
	public void TestB() {
		Retrofit retrofit = retrofitHelper.getRetrofit(ApiSimpleService.BASE_URL);
		
		ApiSimpleService apiSimpleService = retrofit.create(ApiSimpleService.class);
		
		Call<Phone> call = apiSimpleService.getPhone();
		
		Phone phone = null;
		
		try {
			phone = call.execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug(phone.toString());
	}
	
	@Test
    public void testC() {
        // Retrofit 객체 생성
        // --> import retrofit2.Retrofit;
        Retrofit retrofit = retrofitHelper.getRetrofit(ApiSimpleService.BASE_URL);
        
        // Service 객체를 생성한다. 구현체는 Retrofit이 자동으로 생성해 준다.
        ApiSimpleService apiSimpleService = retrofit.create(ApiSimpleService.class);

        // 메서드를 호출해서 call 객체를 리턴받는다.
        // --> import retrofit2.Call;
        Call<GradeList> call = apiSimpleService.getGradeList();

        // call객체를 통해 JSON 파싱이 완료된 Beans 객체를 리턴받는다.
        GradeList gradeList = null;
        
        try {
            gradeList = call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        log.debug(gradeList.toString());
    }

}
