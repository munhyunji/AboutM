package study.spring.springhelper.service;

import java.util.List;

import study.spring.springhelper.model.Professor;


public interface ProfessorService {
	
	
	public Professor getProfessorItem(Professor input) throws Exception;
	
	public List<Professor> getProfessorList(Professor input) throws Exception;
	
	public int getProfessorCount(Professor input) throws Exception;
	
	
	/*교수 데이터 등록하기*/
	public int addProfessor(Professor input) throws Exception;
	
	/*교수 데이터 수정하기*/
	public int editProfessor(Professor input) throws Exception;
	
	/*교수 데이터 삭제하기*/
	public int deleteProfessor(Professor input) throws Exception;
}

