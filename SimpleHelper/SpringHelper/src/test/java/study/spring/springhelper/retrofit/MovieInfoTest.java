package study.spring.springhelper.retrofit;

import java.io.IOException;
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
import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.model.MovieInformation;
import study.spring.springhelper.model.MovieInformation.MovieInfoResult.MovieInfo;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.ApiKobisService;

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
public class MovieInfoTest {
	
	@Autowired
	RetrofitHelper retrofitHelper;
	
	@Test
	public void testA() {
		//api 연동객체 생성
		Retrofit retrofit = retrofitHelper.getRetrofit(ApiKobisService.BASE_URL);
		ApiKobisService apiKobisService = retrofit.create(ApiKobisService.class);
		
		//검색 파라미터 처리
		String movieCd = "20216362";
		Call<MovieInformation> call = apiKobisService.getMovieInformation(movieCd);
		MovieInformation movieInformation = null;
		try {
			movieInformation = call.execute().body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//조회결과를 반복문을 사용해서 출력.. 
		if(movieInformation !=null) {
			MovieInfo list = movieInformation.getMovieInfoResult().getMovieInfo();
			log.debug(">>> " + list.toString());
			
		}
		
		
	}

}
