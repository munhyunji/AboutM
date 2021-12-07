package study.spring.springhelper.model;

import lombok.Data;

@Data
public class Professor {
	private int profno;
	private String name;
	private String userid;
	private String position;
	private int sal;
	private String hiredate;
	private Integer comm;
	private int deptno;
	
	//join절에 따른 추가 컬럼
	private String dname;
	
	private String loc;
	// 페이지구현을 위한 static 변수
	private static int offset;
	
	private static int listCount;

	public static int getOffset() {
		return offset;
	}

	public static void setOffset(int offset) {
		Professor.offset = offset;
	}

	public static int getListCount() {
		return listCount;
	}

	public static void setListCount(int listCount) {
		Professor.listCount = listCount;
	}
}
