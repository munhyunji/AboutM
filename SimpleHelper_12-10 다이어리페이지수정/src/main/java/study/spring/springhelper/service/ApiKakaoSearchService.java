package study.spring.springhelper.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import study.spring.springhelper.model.Image;

public interface ApiKakaoSearchService {
	
	public static final String BASE_URL = "https://dapi.kakao.com";
	
	@Headers("Authorization: KakaoAK 078a07f8738d2dceaa2fdd9a7c9d2e65")
	@GET("/v2/search/image")
	Call<Image> getImage(@Query("query") String query , @Query("page") int page, @Query("size") int size);

}
