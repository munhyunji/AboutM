package study.spring.springhelper.model;

import lombok.Data;

@Data
public class Department {
	private int deptno;
	private String dname;
	private String loc;
	
	// 페이지 구현이 필요한 경우 아래 속성들을 추가한다
	private static int offset;
	private static int listCount;
	
	public static int getOffset( ) {
		return offset;
	}
	
	public static void setOffset(int offset) {
		Department.offset = offset;
	}
	
	public static int getListCount( ) {
		return listCount;
	}
	
	public static void setListCount(int listCount) {
		Department.listCount = listCount;
	}
}
