package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.SearchDailyBoxOfficeList.BoxOfficeResult.DailyBoxOfficeList;

public interface MovieRankService {
	public void collectMovieRank(List<DailyBoxOfficeList> list) throws Exception;
}
