package study.spring.springhelper.helper;

import lombok.Data;

@Data
public class UploadItem {
	private String fieldName;
	private String originName;
	private String contentType;
	private long fileSize;
	private String filePath;
	private String thumbnailPath;
	private String fileUrl;
	private String thumbnailUrl;
	
}
