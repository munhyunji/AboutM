package study.spring.springhelper.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import study.spring.springhelper.helper.UploadItem;
import study.spring.springhelper.helper.WebHelper;

@Controller
public class UploadController {
	@Autowired
	WebHelper webHelper;

	// 업로드 폼을 구성하는 페이지
	@RequestMapping(value = "/upload/upload.do", method = RequestMethod.GET)
	public String upload() {
		return "upload/upload";
	}

	// 업로드 폼을 구성하는 action 페이지
	@RequestMapping(value = "/upload/upload_ok.do", method = RequestMethod.POST)
	public ModelAndView uploadOK(Model model, @RequestParam(required = false) String subject,
			@RequestParam(required = false) MultipartFile photo) {

		// 업로드 파일ㅈ ㅓ장하기
		if (photo.getOriginalFilename().isEmpty()) {
			return webHelper.redirect(null, "업로드된 파일이 없습니다");
		}
		// 업로드 경로 정보 처리하기

		File targetFile = new File(webHelper.getUploadDir(), photo.getOriginalFilename());

		// 업로드된 파일을 지정된 경로로 복사한다
		try {
			photo.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
			return webHelper.redirect(null, "업로드된 파일 저장에 실패했습니다.");
		}

		String absPath = targetFile.getAbsolutePath().replace("\\", "/");

		String filePath = absPath.replace(webHelper.getUploadDir(), "");

		// 업로드 결과를 beans에 저장
		UploadItem item = new UploadItem();
		item.setContentType(photo.getContentType());
		item.setFieldName(photo.getName());
		item.setFileSize(photo.getSize());
		item.setOriginName(photo.getOriginalFilename());
		item.setFilePath(filePath);

		String fileUrl = webHelper.getUploadUrl(filePath);
		item.setFileUrl(fileUrl);

		// view 처리
		model.addAttribute("subject", subject);
		model.addAttribute("item", item);

		// 뷰 호출
		return new ModelAndView("upload/upload_ok");
	}
	
	@RequestMapping(value="/upload/use_helper.do", method = RequestMethod.GET)
	public String useHelper() {
		return "upload/use_helper";
	}
	
	@RequestMapping(value="/upload/use_helper_ok.do", method = RequestMethod.POST)
	public ModelAndView useHelperOk(Model model,
			@RequestParam(required=false)MultipartFile photo) {
		
		// 1)업로드처리
		//업로드 결과가 저장된 beasn를 리턴받는다
		UploadItem item = null;
		try {
			item = webHelper.saveMultipartFile(photo);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return webHelper.redirect(null, "업로드된 파일이 없습니다");
		} catch (Exception e) {
			e.printStackTrace();
			return webHelper.redirect(null, "업로드에 실패했습니다");
		}
		
		// 신규 파일 형식이 이미지인경우 썸네일 이미지 생성하기
		if (item != null && item.getContentType().indexOf("image") > -1) {
			String thumbnailPath = null;
			
			try {
				thumbnailPath = webHelper.createThumbnailString(item.getFilePath(), 240, 240, true);
			} catch (Exception e) {
				e.printStackTrace();
				return webHelper.redirect(null, "썸네일 이미지 생성에 실패했습니다");
			}
			
			// 썸네일 경로를 URL로 변환
			String thumbnailUrl = webHelper.getUploadUrl(thumbnailPath);
			//리턴할 객체에 썸네일 정보 추가
			item.setThumbnailPath(thumbnailPath);
			item.setThumbnailUrl(thumbnailUrl);
		}
			
		//2) View처리
		model.addAttribute("item", item);
		return new ModelAndView("upload/use_helper_ok");
		
	}
	
	

}
