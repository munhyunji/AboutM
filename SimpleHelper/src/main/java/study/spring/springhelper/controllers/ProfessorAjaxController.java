package study.spring.springhelper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.PageData;
import study.spring.springhelper.helper.RegexHelper;
import study.spring.springhelper.helper.WebHelper;
import study.spring.springhelper.model.Department;
import study.spring.springhelper.model.Professor;
import study.spring.springhelper.service.DepartmentService;
import study.spring.springhelper.service.ProfessorService;

@Controller
public class ProfessorAjaxController {
	@Autowired WebHelper webHelper;
	@Autowired RegexHelper regexHelper;
	@Autowired ProfessorService professorService;
	@Autowired DepartmentService departmentService;
	
	// /프로젝트 일므에 해당하는 context 변수주입
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	//목록페이지
	@RequestMapping(value = "/professor_ajax/list.do", method = RequestMethod.GET)
	public ModelAndView list(Model model,
			//검색어
			@RequestParam(value="keyword", required=false) String keyword,
			//페이지구현에서 사용할 현재 페이지 번호
		@RequestParam(value="page", defaultValue="1") int nowPage) {
		//페이지 구현에 필요한 변수값 생성
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
			
			//페이지번호 계산
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);
			
			Professor.setOffset(pageData.getOffset());
			Professor.setListCount(pageData.getListCount());
			
			
			//데이타 조회 검색조건없이 모든 학과 조회
			output = professorService.getProfessorList(null);
		} catch (Exception e) {
		return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		
		return new ModelAndView("professor/list_ajax");
		
		//view 처리
		
		}
	
	//상세페이지
		@RequestMapping(value = "/professor_ajax/view.do", method = RequestMethod.GET)
		public ModelAndView view(Model model,
				@RequestParam(value="profno", defaultValue="0")int profno) {
			// 유효성 검사
			if (profno == 0) {
				return webHelper.redirect(null, "교수번호가 없습니다.");
			}
			
			Professor input = new Professor(); 
				input.setProfno(profno);
			
			Professor output = null;
			

			try {
				//데이타 조회 검색조건없이 모든 학과 조회
				output = professorService.getProfessorItem(input);
			} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			model.addAttribute("output", output);
			return new ModelAndView("professor/view_ajax");
		}
		
		//작성 폼페이지
		@RequestMapping(value = "/professor_ajax/add.do", method = RequestMethod.GET)
		public ModelAndView add(Model model) {
			
			List<Department> output = null;
			
			try {
				//데이타 조회 검색조건없이 모든 학과 조회
				output = departmentService.getDepartmentList(null);
			} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			model.addAttribute("output", output);
			return new ModelAndView("professor/add_ajax");
		}
		
		
		
		//수정폼페이지
		@RequestMapping(value = "/professor_ajax/edit.do", method = RequestMethod.GET)
		public ModelAndView edit(Model model,
				@RequestParam(value="profno", defaultValue="0") int profno) {

			//파라미터 유효성 검사
			if (profno == 0) {
				return webHelper.redirect(null, "교수번호가 없습니다.");
			}
			
			Professor input = new Professor();
			input.setProfno(profno);
			
			//교수 결과를 조회할 객체 선언
			Professor output = null;
			
			//학과목록을 선택할수있는ㄷ 드롭다운을 위하 조회결과를 저장할 개체선언
			List<Department> deptList = null;
			
			
			

			try {
				//데이타 조회 검색조건없이 모든 학과 조회
				output = professorService.getProfessorItem(input);
				//드롭다운을 위한 학과목록 조회
				deptList = departmentService.getDepartmentList(null);
			} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			model.addAttribute("output", output);
			model.addAttribute("deptList", deptList);
			return new ModelAndView("professor/edit_ajax");
		}
		
		
		
					
}
