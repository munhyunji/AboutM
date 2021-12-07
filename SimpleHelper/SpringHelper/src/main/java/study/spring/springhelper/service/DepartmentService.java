package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.Department;


public interface DepartmentService {

	public Department getDepartmentItem(Department input) throws Exception;
	
	public List<Department> getDepartmentList(Department input) throws Exception;
	
	public int getDepartmentCount(Department input) throws Exception;
	
	
	/*학과 데이터 등록하기*/
	public int addDepartment(Department input) throws Exception;
	
	/*학과 데이터 수정하기*/
	public int editDepartment(Department input) throws Exception;
	
	/*학과 데이터 삭제하기*/
	public int deleteDepartment(Department input) throws Exception;
}
