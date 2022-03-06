package study.spring.springhelper.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.RegexHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Professor;
import study.spring.springhelper.service.DepartmentService;
import study.spring.springhelper.service.ProfessorService;

@RestController
public class ProfessorRestController {
	@Autowired
	WebHelper webHelper;
	@Autowired
	RegexHelper regexHelper;
	@Autowired
	ProfessorService professorService;
	@Autowired
	DepartmentService departmentService;

	// 목록페이지
	@RequestMapping(value = "/professor", method = RequestMethod.GET)
	public Map<String, Object> get_list(// 검색어
			@RequestParam(value = "keyword", required = false) String keyword,
			// 페이지구현에서 사용할 현재 페이지 번호
			@RequestParam(value = "page", defaultValue = "1") int nowPage) {

		// 페이지 구현에 필요한 변수값 생성
		int totalCount = 0;
		int listCount = 10;
		int pageCount = 5;

		// 데이터 조회
		Professor input = new Professor();
		input.setName(keyword);

		List<Professor> output = null;
		PageData pageData = null;

		try {
			// 전체 게시글 수 조회
			totalCount = professorService.getProfessorCount(input);

			// 페이지번호 계산
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			Professor.setOffset(pageData.getOffset());
			Professor.setListCount(pageData.getListCount());

			// 데이타 조회 검색조건없이 모든 학과 조회
			output = professorService.getProfessorList(input);
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}

		// 3 JOSN 출력하기
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("keyword", keyword);
		data.put("item", output);
		data.put("meta", pageData);

		return webHelper.getJsonData(data);

		// view 처리
	}

	// 상세페이지
	@RequestMapping(value = "/professor/{profno}", method = RequestMethod.GET)
	public Map<String, Object> get_item(@PathVariable("profno") int profno) {
		//데이터 조회하기
		Professor input = new Professor();
		input.setProfno(profno);

		Professor output = null;

		try {
			// 데이타 조회 검색조건없이 모든 학과 조회
			output = professorService.getProfessorItem(input);
		} catch (Exception e) {
			return webHelper.getJsonError(e.getLocalizedMessage());
		}
		//Json출력하기
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("item", output);
		

		return webHelper.getJsonData(data);

	}

	// 작성폼action 페이지
	@RequestMapping(value = "/professor", method = RequestMethod.POST)
	public Map<String, Object> post(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="userid", defaultValue="") String userid,
			@RequestParam(value="position", defaultValue="") String position,
			@RequestParam(value="sal", defaultValue="0") int sal,
			@RequestParam(value="hiredate", defaultValue="") String hiredate,
			@RequestParam(value="comm", defaultValue="") Integer comm,
			@RequestParam(value="deptno", defaultValue="0") int deptno) {
		
		 /** 1) 사용자가 입력한 파라미터 유효성 검사 */
        // 일반 문자열 입력 컬럼 --> String으로 파라미터가 선언되어 있는 경우는 값이 입력되지 않으면 빈 문자열로 처리된다.
        if (!regexHelper.isValue(name))     { return webHelper.getJsonWarning("교수 이름을 입력하세요."); }
        if (!regexHelper.isKor(name))       { return webHelper.getJsonWarning("교수 이름은 한글만 가능합니다."); }
        if (!regexHelper.isValue(userid))   { return webHelper.getJsonWarning("교수 아이디를 입력하세요."); }
        if (!regexHelper.isEngNum(userid))  { return webHelper.getJsonWarning( "교수 아이디는 영어와 숫자로만 가능합니다."); }
        if (!regexHelper.isValue(position)) { return webHelper.getJsonWarning( "직급을 입력하세요."); }
        if (!regexHelper.isValue(hiredate)) { return webHelper.getJsonWarning("입사일을 입력하세요."); }

        // 숫자형으로 선언된 파라미터()
        if (sal == 0)                       { return webHelper.getJsonWarning( "급여를 입력하세요."); }
        if (sal < 0)                        { return webHelper.getJsonWarning( "급여는 0보다 작을 수 없습니다."); }
        if (comm < 0)                       { return webHelper.getJsonWarning( "보직수당은 0보다 작을 수 없습니다."); }
        if (deptno == 0)                    { return webHelper.getJsonWarning( "소속 학과 번호를 입력하세요."); }

        /** 2) 데이터 저장하기 */
        // 저장할 값들을 Beans에 담는다.
        Professor input = new Professor();
        input.setName(name);
        input.setUserid(userid);
        input.setPosition(position);
        input.setSal(sal);
        input.setHiredate(hiredate);
        input.setComm(comm);
        input.setDeptno(deptno);
        
        //저장된 결과를 조회하기 위한 객체
        Professor output = null;

        try {
            // 데이터 저장
            // --> 데이터 저장에 성공하면 파라미터로 전달하는 input 객체에 PK값이 저장된다.
            professorService.addProfessor(input);
            
            //데이터 조회
            output = professorService.getProfessorItem(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
            
	} 
        /** 3) 결과를 확인하기 위한 JSON 출력 */
        // 저장 결과를 확인하기 위해서 데이터 저장시 생성된 PK값을 상세 페이지로 전달해야 한다.
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", output);
	
		return webHelper.getJsonData(map);
	}
	// 수정폼 action페이지
	@RequestMapping(value = "/professor", method = RequestMethod.PUT)
	public Map<String, Object> put(
			@RequestParam(value="profno", defaultValue="0") int profno,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="userid", defaultValue="") String userid,
			@RequestParam(value="position", defaultValue="") String position,
			@RequestParam(value="sal", defaultValue="0") int sal,
			@RequestParam(value="hiredate", defaultValue="") String hiredate,
			@RequestParam(value="comm", defaultValue="0") Integer comm,
			@RequestParam(value="deptno", defaultValue="0") int deptno) {
		
		 /** 1) 사용자가 입력한 파라미터 유효성 검사 */
		if(profno == 0 )                    { return webHelper.getJsonWarning("교수번호가 없습니다"); }
		
		 /** 1) 사용자가 입력한 파라미터 유효성 검사 */
        // 일반 문자열 입력 컬럼 --> String으로 파라미터가 선언되어 있는 경우는 값이 입력되지 않으면 빈 문자열로 처리된다.
        if (!regexHelper.isValue(name))     { return webHelper.getJsonWarning("교수 이름을 입력하세요."); }
        if (!regexHelper.isKor(name))       { return webHelper.getJsonWarning("교수 이름은 한글만 가능합니다."); }
        if (!regexHelper.isValue(userid))   { return webHelper.getJsonWarning("교수 아이디를 입력하세요."); }
        if (!regexHelper.isEngNum(userid))  { return webHelper.getJsonWarning( "교수 아이디는 영어와 숫자로만 가능합니다."); }
        if (!regexHelper.isValue(position)) { return webHelper.getJsonWarning( "직급을 입력하세요."); }
        if (!regexHelper.isValue(hiredate)) { return webHelper.getJsonWarning("입사일을 입력하세요."); }

        // 숫자형으로 선언된 파라미터()
        if (sal == 0)                       { return webHelper.getJsonWarning( "급여를 입력하세요."); }
        if (sal < 0)                        { return webHelper.getJsonWarning( "급여는 0보다 작을 수 없습니다."); }
        if (comm < 0)                       { return webHelper.getJsonWarning( "보직수당은 0보다 작을 수 없습니다."); }
        if (deptno == 0)                    { return webHelper.getJsonWarning( "소속 학과 번호를 입력하세요."); }


        /** 2) 데이터 저장하기 */
        // 저장할 값들을 Beans에 담는다.
        Professor input = new Professor();
        input.setProfno(profno);
        input.setName(name);
        input.setUserid(userid);
        input.setPosition(position);
        input.setSal(sal);
        input.setHiredate(hiredate);
        input.setComm(comm);
        input.setDeptno(deptno);
        
        // 수정된 결과를 조회하기위한 객체
        Professor output = null;

        try {
            // 데이터 수정        
            professorService.editProfessor(input);
            
            // 데이터 조회
            output = professorService.getProfessorItem(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }
			
		// 결과를 확인하기 위한 JSON출력	
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", output);
		
		return webHelper.getJsonData(map);
		
	}

	// 삭제처리
	 @RequestMapping(value = "/professor", method = RequestMethod.DELETE)
	    public Map<String, Object> delete(
	            @RequestParam(value="profno", defaultValue="0") int profno) {
	        
	        /** 1) 파라미터 유효성 검사 */
	        // 이 값이 존재하지 않는다면 데이터 삭제가 불가능하므로 반드시 필수값으로 처리해야 한다.
	        if (profno == 0) {
	            return webHelper.getJsonWarning("교수 번호가 없습니다.");
	        }

	        /** 2) 데이터 삭제하기 */
	        // 데이터 삭제에 필요한 조건값을 Beans에 저장하기
	        Professor input = new Professor();
	        input.setProfno(profno);

	        try {
	            professorService.deleteProfessor(input); // 데이터 삭제
	        } catch (Exception e) {
	            return webHelper.getJsonError(e.getLocalizedMessage());
	        }

	        /** 3) 결과를 확인하기 위한 JSON 출력 */
	        // 확인할 대상이 삭제된 결과값만 OK로 전달
	        return webHelper.getJsonData();
	    }
}
