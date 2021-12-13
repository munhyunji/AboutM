package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.Diary;

public interface DiaryService {
	public Diary getDiaryItem(Diary input) throws Exception;
	public List<Diary> getDiaryList(Diary input) throws Exception;
	public int getDiaryCount(Diary input) throws Exception;
	public int addDiary(Diary input) throws Exception;
	public int editDiary(Diary input) throws Exception;
	public int deleteDiary(Diary input) throws Exception;
}
