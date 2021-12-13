package study.spring.springhelper.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.springhelper.model.Diary;
import study.spring.springhelper.service.DiaryService;

@Slf4j
@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public Diary getDiaryItem(Diary input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diary> getDiaryList(Diary input) throws Exception {
		List<Diary> result=null;
		
		try {
			result = sqlSession.selectList("DiaryMapper.selectList", input);
			
			if ( result == null) {
				throw new NullPointerException("result=null");
			}
			
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}

	@Override
	public int addDiary(Diary input) throws Exception {
		int result=0;
		try {
			result = sqlSession.insert("DiaryMapper.insertItem", input);
			
			if ( result == 0) {
				throw new NullPointerException("result=null");
			}
			
		} catch (NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int editDiary(Diary input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDiary(Diary input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDiaryCount(Diary input) throws Exception {
		  int result = 0;
	        
	        try {
	            result = sqlSession.selectOne("DiaryMapper.selectCountAll", input);
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }
	        
	        return result;
	}

}
