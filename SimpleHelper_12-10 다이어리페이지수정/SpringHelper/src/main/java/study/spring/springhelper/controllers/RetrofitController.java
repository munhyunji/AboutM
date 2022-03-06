package study.spring.springhelper.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import retrofit2.Call;
import retrofit2.Retrofit;
import study.spring.springhelper.helper.RetrofitHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Image;
import study.spring.springhelper.model.SearchDailyBoxOfficeList;
import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;
import study.spring.springhelper.service.ApiKakaoSearchService;
import study.spring.springhelper.service.ApiKobisService;

@Controller
public class RetrofitController {
	@Autowired
	WebHelper webHelper;

	@Autowired
	RetrofitHelper retrofitHelper;

	/** 영화진흥원 연동 결과를 그래프로 표시하는 페이지 */
	@RequestMapping(value = "/retrofit/daily_box_office.do", method = RequestMethod.GET)
	public String dailyBoxOffice(Model model, @RequestParam(required = false) String date) {

		Retrofit retrofit = retrofitHelper.getRetrofit(ApiKobisService.BASE_URL);

		ApiKobisService apiKobisService = retrofit.create(ApiKobisService.class);

		if (date == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -1);

			date = String.format("%04d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
					c.get(Calendar.DAY_OF_MONTH));
		}

		String targetDt = date.replace("-", "");

		Call<SearchDailyBoxOfficeList> call = apiKobisService.getSearchDailyBoxOfficeList(targetDt);
		SearchDailyBoxOfficeList searchDailyBoxOfficeList = null;

		try {
			searchDailyBoxOfficeList = call.execute().body();
		} catch (IOException e) {

			e.printStackTrace();
		}
		List<DailyBoxOfficeList> list = null;

		if (searchDailyBoxOfficeList != null) {
			list = searchDailyBoxOfficeList.getBoxOfficeResult().getDailyBoxOfficeList();
		}
		
		int size = list.size();
		String[] audiCnt = new String[size];
		String[] movieNm = new String[size];
		
		for(int i=0; i<size; i++) {
			DailyBoxOfficeList item = list.get(i);
			movieNm[i] = " ' " + item.getMovieNm()+" ' ";
			audiCnt[i] = String.valueOf(item.getAudiCnt());
		}
		String movieNmStr = String.join(", ", movieNm);
		String audiCntStr = String.join(", ", audiCnt);
		
		
		
		model.addAttribute("date", date);
		model.addAttribute("targetDt", targetDt);
		model.addAttribute("list", list);
		model.addAttribute("movieNmStr",movieNmStr);
		model.addAttribute("audiCntStr",audiCntStr);

		return "retrofit/daily_box_office";
	}
		@RequestMapping(value ="/retrofit/kakao_image_search.do", method= RequestMethod.GET)
		public String kakaoImageSearch(Model model, @RequestParam(required=false, defaultValue="")String query) {
			Retrofit retrofit = retrofitHelper.getRetrofit(ApiKakaoSearchService.BASE_URL);
			
			ApiKakaoSearchService apiKakaoSearchService = retrofit.create(ApiKakaoSearchService.class);
			
			//api 연동
			Image image = null;
			
			if(!query.equals("")) {
				Call<Image> call = apiKakaoSearchService.getImage(query, 1, 50);
				
				try {
					image = call.execute().body();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// view처리 
			model.addAttribute("query", query);
			model.addAttribute("image", image);
			
			
			return "retrofit/kakao_image_search";
		}
}
