package study.spring.springhelper.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


@Data
public class MovieInformation {
	 @SerializedName("movieInfoResult")  
	 private MovieInfoResult movieInfoResult; 

	    @Data
	    public class MovieInfoResult {
	        @SerializedName("movieInfo")   private MovieInfo movieInfo;
	        

	        @Data
	        public class MovieInfo {
	        
	        @SerializedName("movieCd")     private String movieCd;
	        @SerializedName("showTm")     private String showTm;
	        @SerializedName("nations")   private List<Nations> nations;
		    @SerializedName("genres")   private List<Genres> genres;
		    @SerializedName("directors")   private List<Directors> directors;
		    @SerializedName("actors")   private  List<Actors> actors;
		    @SerializedName("showTypes")   private  List<ShowType> showTypes;
		    @SerializedName("companys")   private  List<Company> companys;
		    @SerializedName("audits")   private  List<Audits> audits;
		    @SerializedName("staffs")   private  List<Staff> staffs;
	          
		    @Data
		    public class Nations {
		    	@SerializedName("nationNm") private String nationNm;
		    }
		    @Data
		    public class Genres {
		    	@SerializedName("genreNm") private String genreNm;
		    }
		    @Data
		    public class Directors {
		    	@SerializedName("peopleNm") private String peopleNm;
		    }
		    @Data
		    public class Actors {
		    	@SerializedName("peopleNm") private String peopleNm;
		    }
		    @Data
		    public class ShowType {
		    	@SerializedName("showTypeGroupNm") private String showTypeGroupNm;
		    }
		    @Data
		    public class Company {
		    	@SerializedName("companyNm") private String companyNm;
		    }
		    @Data
		    public class Audits {
		    	@SerializedName("watchGradeNm") private String watchGradeNm;
		    }
		    @Data
		    public class Staff {
		    	@SerializedName("peopleNm") private String peopleNm;
		    }
		    
		   
	        }
	        
	        
	        
	        
	    }
	    
	    
}
