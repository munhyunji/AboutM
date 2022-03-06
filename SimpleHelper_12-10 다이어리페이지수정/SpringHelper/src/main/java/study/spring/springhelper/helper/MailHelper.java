package study.spring.springhelper.helper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailHelper {
	JavaMailSender mailSender;
	
	public MailHelper(JavaMailSender sender) {
		this.mailSender = sender;
	}
	
	//메일을 발송한다
	public void sendMail(String receiver, String subject, String content )
		throws Exception {
		
		log.debug("-------------------------------");
		log.debug(String.format("To: %s", receiver));
		log.debug(String.format("Subject: %s", subject));
		log.debug(String.format("Content: %s", content));
		log.debug("-------------------------------");
		
		
		//생성자를 통해 주입된 설정 객체를 원래 형태로 변환
		JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) this.mailSender;
		// 사용자이름ㅇ르 가져온다
		String sender = mailSenderImpl.getUsername();
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		// 제목, 내용, 수신자, 발신자 설정
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.setFrom(new InternetAddress(sender));
		helper.setTo(new InternetAddress(receiver));
		
		//메일 보내기
		mailSender.send(message);
		
	}
}
