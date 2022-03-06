package study.spring.springhelper.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.Department;
import study.spring.springhelper.model.Professor;
import study.spring.springhelper.service.ProfessorService;

@Slf4j
@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	SqlSession sqlSession;
	
	
	
	 @Override
	    public Professor getProfessorItem(Professor input) throws Exception {
		 Professor result = null;

	        try {
	            result = sqlSession.selectOne("ProfessorMapper.selectItem", input);

	            if (result == null) {
	                throw new NullPointerException("result=null");
	            }
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("조회된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }

	        return result;
	    }

	 @Override
	    public List<Professor> getProfessorList(Professor input) throws Exception {
	        List<Professor> result = null;

	        try {
	            result = sqlSession.selectList("ProfessorMapper.selectList", input);

	            if (result == null) {
	                throw new NullPointerException("result=null");
	            }
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("조회된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }

	        return result;
	    }

	 @Override
	    public int getProfessorCount(Professor input) throws Exception {
	        int result = 0;
	        
	        try {
	            result = sqlSession.selectOne("ProfessorMapper.selectCountAll", input);
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }
	        
	        return result;
	    }


	 @Override
	    public int addProfessor(Professor input) throws Exception {
	        int result = 0;

	        try {
	            result = sqlSession.insert("ProfessorMapper.insertItem", input);

	            if (result == 0) {
	                throw new NullPointerException("result=0");
	            }
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("저장된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 저장에 실패했습니다.");
	        }

	        return result;
	    }

	 @Override
	    public int editProfessor(Professor input) throws Exception {
	        int result = 0;

	        try {
	            result = sqlSession.update("ProfessorMapper.updateItem", input);

	            if (result == 0) {
	                throw new NullPointerException("result=0");
	            }
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("수정된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 수정에 실패했습니다.");
	        }

	        return result;
	    }

	 @Override
	    public int deleteProfessor(Professor input) throws Exception {
	        int result = 0;

	        try {
	            // 교수 삭제 전 자신을 참조하는 학생들의 profno컬럼을 null로 수정
	            sqlSession.update("StudentMapper.unsetProfessor", input);
	            
	            result = sqlSession.delete("ProfessorMapper.deleteItem", input);

	            if (result == 0) {
	                throw new NullPointerException("result=0");
	            }
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("삭제된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 삭제에 실패했습니다.");
	        }

	        return result;
	    }
}
