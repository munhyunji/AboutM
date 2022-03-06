package study.spring.springhelper.helper;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

@Slf4j
public class WebHelper {
    /** 기본 인코딩 타입 설정 */
    private String encType;

    /** JSP의 request 내장 객체 */
    // --> import javax.servlet.http.HttpServletRequest;
    private HttpServletRequest request;

    /** JSP의 response 내장 객체 */
    // --> import javax.servlet.http.HttpServletResponse;
    private HttpServletResponse response;
    
    /** 업로드 된 결과물이 저장될 폴더 */
    private String uploadDir;

    /** 업로드 된 파일이 식별될 URL경로 */
    private String uploadPath;
   

	// ----------- 싱글톤 객체 생성 시작 ----------
    /**
     * 싱글톤 객체가 생성될 때 호출되는 메서드로 JSP의 주요 내장객체를 멤버변수에 연결한다.
     *
     * @param request
     * @param response
     */
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request; // JSP 내장객체 참조하기
        this.response = response; // JSP 내장객체 참조하기

        String methodName = request.getMethod(); // GET방식인지, POST방식인지 조회한다.
        String url = request.getRequestURL().toString(); // 현재 URL을 획득한다.
        String queryString = request.getQueryString(); // URL에서 "?"이후의 GET파라미터 문자열을 모두 가져온다.

        if (queryString != null) { // 가져온 값이 있다면 URL과 결합하여 완전한 URL을 구성한다.
            url = url + "?" + queryString;
        }

        log.debug(String.format("[%s] %s", methodName, url)); // 획득한 정보를 로그로 표시한다.

        /** 내장객체 초기화 -> utf-8 설정 */
        // --> import java.io.UnsupportedEncodingException;
        try {
            this.request.setCharacterEncoding(this.encType);
            this.response.setCharacterEncoding(this.encType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /** Getter, Setter 메서드 */
    public String getEncType() { return encType; }

    public void setEncType(String encType) { this.encType = encType; }

    public HttpServletRequest getRequest() { return request; }

    public void setRequest(HttpServletRequest request) { this.request = request; }

    public HttpServletResponse getResponse() { return response; }

    public void setResponse(HttpServletResponse response) { this.response = response; }

    public String getUploadDir() { return uploadDir; }

    public void setUploadDir(String uploadDir) { this.uploadDir = uploadDir; }

    public String getUploadPath() { return uploadPath; }

    public void setUploadPath(String uploadPath) { this.uploadPath = uploadPath; }

    /**
     * 업로드 폴더 하위에 저장되어 있는 파일이름을 전달받아 Web에서 접근 가능한 경로로 리턴한다.
     */
    public String getUploadUrl(String filePath) {
        // URL상의 업로드 폴더와 파일 이름을 결합하여 파일 객체 생성
        File f = new File(this.uploadPath, filePath);
        // 결합된 경로 추출
        String path = f.getPath();
        // window의 경우 경로 구문을 역슬래시로 하는데, 이는 웹에 노출할 수 있는 형태가 아니므로
        // 역슬래시를 슬래시로 변환하여 반환한다.
        return path.replace("\\", "/");
    }
	/**
     * 메시지 표시 후, 페이지를 지정된 곳으로 이동한다.
     *
     * @param url - 이동할 페이지의 URL, Null일 경우 이전페이지로 이동
     * @param msg - 화면에 표시할 메시지. Null일 경우 표시 안함
     * @return ModelAndView
     */
    public ModelAndView redirect(String url, String msg) {

        // 획득한 정보를 로그로 표시한다.
        log.debug(String.format(" --> [redirect] %s >> %s", url, msg));

        // 가상의 View로 만들기 위한 HTML 태그 구성
        String html = "<!doctype html>";
        html += "<html>";
        html += "<head>";
        html += "<meta charset='" + this.encType + "'>";

        // 메시지 표시
        if (msg != null) {
            html += "<script type='text/javascript'>alert('" + msg + "');</script>";
        }

        // 페이지 이동
        if (url != null) {
            html += "<meta http-equiv='refresh' content='0; url=" + url + "' />";
        } else {
            html += "<script type='text/javascript'>history.back();</script>";
        }

        html += "</head>";
        html += "<body></body>";
        html += "</html>";

        return this.virtualView(html);
    }

    /**
     * 파라미터로 받은 내용을 가상의 View로 생성후 리턴한다.
     * 브라우저에게 전달할 HTML,CSS,JS 조합을 출력하기 위해 사용한다.
     * @param body - 브라우저에게 전달할 HTML,CSS,JS 조합 문자열
     * @return ModelAndView
     */
    public ModelAndView virtualView(final String body) {
        /** 가상의 View를 익명 클래스 방식으로 생성하여 리턴 */
        // --> import org.springframework.web.servlet.View;
        // --> import org.springframework.web.servlet.view.AbstractView;
        View view = new AbstractView() {
            @Override
            protected void renderMergedOutputModel(Map<String, Object> map,
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
                PrintWriter out = response.getWriter();
                out.println(body);
                out.flush();
            }
        };

        // 가상의 뷰를 리턴한다.
        return new ModelAndView(view);
    }
    
    public Map<String, Object> getJsonData(int statusCode, String rt, Map<String, Object> data) {
    	//1 http 헤더설정
    	response.setContentType("application/json");
    	
    	// http 상태코드 서렂ㅇ
    	response.setStatus(statusCode);
    	
    	//CrossDomain에의한 접근허용
    	 this.response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
         this.response.setHeader("Access-Control-Max-Age", "3600");
         this.response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
         this.response.setHeader("Access-Control-Allow-Origin", "*");

    	//2 파라미터로 전달된 MAP객체어 rt와 pubDAte값을 추가한 새로운 Map생성
         /** 2) 파라미터로 전달된 Map 객체에 rt와 pubDate값을 추가한 새로운 Map 생성하기 */
         // JSON 생성일시를 위한 현재 시각 문자열 만들기
         Calendar c = Calendar.getInstance();
         String pubDate = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                             c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                             c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));

         // JSON 구성을 위한 컬렉션 객체 생성
         Map<String, Object> map = new HashMap<String, Object>();

         // 결과코드 추가
         map.put("rt", rt);

         // 생성일시 추가
         map.put("pubDate", pubDate);

         // data가 전달되었다면 map에 병합한다.
         if (data != null) {
             map.putAll(data);
         }
         
         return map;
    }
    
    public Map<String, Object> getJsonData(Map<String, Object> data) {
    	return this.getJsonData(200, "OK", data);
    }
    
    //입력 수정 삭제 결과를 위한 메서드 overload
    public Map<String, Object> getJsonData() {
    	return this.getJsonData(200, "OK", null);
    }
    
    //오류메세지를 포함해야하는경우
    public Map<String, Object> getJsonError(String rt) {
    	return this.getJsonData(500, rt, null);
    	
    }
    
    //잘못된 요청임을 의미하는 코드 포함하기 
    public Map<String, Object> getJsonWarning(String rt) {
    	return this.getJsonData(400, rt, null);
    }
    
    public UploadItem saveMultipartFile(MultipartFile multipartFile) throws
    NullPointerException, Exception {
    	UploadItem item = null;
    	
    	//업로드 파일 저장하기
    	//파일의 원본 이름 추출
    	String originName = multipartFile.getOriginalFilename();
    	
    	// 1)업로드된 파일 이 존재하는지 확인한다
    	if (originName.isEmpty()) {
    		throw new NullPointerException("업로드된 파일이 없습니다");
    	}
    	
    	//2) 동일한 이름의 파일ㅇ ㅣ존재하는지 검새한다
    	String ext = originName.substring(originName.lastIndexOf("."));
    	String fileName = null; //웹서버에 저장될 파일이름
    	File targetFile = null; // 저장된 파일 정보를 담기위한 file객체
    	int count = 0; //중복된 파일수
    	
    	//일단 무한루프
    	while (true) {
    		//저장될 파일이름 --> 현재시각 + 카운트값 + 확장자
    		fileName = String.format("%d%d%s", System.currentTimeMillis(),count,ext);
    		//업로드 파일이 저장될 폴더 + 파일이름으로 파일객체를 생성한다
    		targetFile = new File(this.uploadDir, fileName);
    		
    		//동일한 이름의 파일이 없다면 반복중단
    		if (!targetFile.exists()) {
    			break;
    		}
    		
    		//if문을 빠져나올경우 중복된 이름의 파일이 존재한다는 의미로 카운트를 1증가
    		count++;
    	}

    	// 3) 업로드된 파일을 결졍된 파일 경로로 저장
    	multipartFile.transferTo(targetFile);
    	
    	// 4)업로드 경로 정보 처리하기 
    	//복사된 파일의 절대경로를 추출한다
    	// 운영체제호환을 위해 역슬래시를 슬래시로 변환한다
    	String absPath = targetFile.getAbsolutePath().replace("\\", "/");
    	
    	//절대 결로에서 이미 지정되어있는 업로드 폴더경로를 삭제한다
    	String filePath = absPath.replace(this.uploadDir, "");
    	
    	//리턴할 정보를 구성한다
    	item = new UploadItem();
    	item.setContentType(multipartFile.getContentType());
    	item.setFieldName(multipartFile.getName());
    	item.setFileSize(multipartFile.getSize());
    	item.setOriginName(originName);
    	item.setFilePath(filePath);
    	
    	//파일 URLPath를 덧붙인 형태로 경로를 가공한다
    	String fileUrl = this.getUploadUrl(filePath);
    	item.setFileUrl(fileUrl);
    	return item;
    }
    
    // 리사이즈딘  썸네일 이미지를 생ㅅ어하고 경로 리턴
    public String createThumbnailString(String path, int width, int height, boolean crop) throws Exception {
    	//1) 썸네일 생성 정보를 로그로 기록
    	log.debug(String.format("[Thumbnail] path: %s, size: %d%d, crop: %s", path, width, height, String.valueOf(crop)));
    	//2) 저장될 썸네일 이미지의 경로 문자열 만들기
    	File loadFile = new File(this.uploadDir, path); // 원본파일의 전체경로
    	String dirPath = loadFile.getParent();
    	String fileName = loadFile.getName();
    	int p = fileName.lastIndexOf("."); //파일이름에서 마지막점 확장자
    	String name = fileName.substring(0, p); //파일명분리 파일에서 마지막점의 위치까지
    	String ext = fileName.substring(p + 1); //확장자분리 파일이름에서마지막점에서 다음부터 끙까지
    	String prefix = crop ? "_crop_" : "_resize_"; //크롭인지 리사이즈인지에 대한  문자열
    	
    	String thumbName = name + prefix + width + "x" + "height" + "." + ext;
    	
    	File f = new File(dirPath, thumbName);
    	String saveFile = f.getAbsolutePath(); //생성될 썸네일파일객체로부터 절대경로 추출 (리털할값)
    	
    	// 생성될 썸네일 이미지의 경로를 로그로 기록
    	log.debug(String.format("[Thumbnail] saveFile: %s", saveFile));
    	
    	//3) 썸네일 이밎 ㅣ생성하고 최종결로 리턴
    	//해당결로에 이미지가 없는 경우만 수행
    	if (!f.exists()) {
    		// 원본이미지 가져오기
    		Builder<File> builder = Thumbnails.of(loadFile);
    		//이미지 크롭 여부에 따라 파라미터에 따라 크롭옵션을 지정한다
    		if (crop == true) {
    			builder.crop(Positions.CENTER); 			
    		}
    		
    		builder.size(width, height); //축소할 사이즈지정
    		builder.useExifOrientation(true); //세로로 촬영된 사진을 회전시킴
    		builder.outputFormat(ext); //파일확장명 지정
    		builder.toFile(saveFile); //저장할 파일 경로지정
    		
    	}
    	
    	//최종적으로 생성된 경로에서 업로드 폴더까지의 경로를 제거한다
    	saveFile = saveFile.replace("\\", "/").replace(this.uploadDir, "");
    	
    	return saveFile;
    }
}
