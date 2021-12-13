package study.spring.springhelper.model;

import lombok.Data;

@Data
public class Diary {

	private int id;
	private String title;
	private String date;
	private String writer;
	private String content;
	
	
	// 페이지 구현이 필요한 경우 아래속성을 추가 (static) 
	private static int offset; //LIMIT절에서 사용할 검색 시작 위치
	private static int listCount; //LIMIT절에서 사용할 검색할 데이터수..
	
	public static int getOffset() {
		return offset;
	}
	public static void setOffset(int offset) {
		Diary.offset = offset;
	}
	public static int getListCount() {
		return listCount;
	}
	public static void setListCount(int listCount) {
		Diary.listCount = listCount;
	}
	
	
	
}
