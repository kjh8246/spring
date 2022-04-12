package com.koreait.idev.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.idev.HomeController;
import com.koreait.idev.model.Gallery;

@Controller
public class GalleryController {

	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);
	//매핑 : 연관시키다.
	
	@GetMapping("gallery")
	public void view() {
		
	}
	
	@PostMapping("gallery")
	public void upload(Gallery vo) {
		//서버에서 전송받은 파일을 저장할 폴더를 정합니다. : c:/upload
		String path = "c:\\upload";		// \는 출력 또는 문자열로 지정할때는 두번 씁니다.
		
		//서버에 전송된 내용 확인 테스트
		logger.info("title" + vo.getTitle());
		for(MultipartFile f : vo.getPics()) {
			logger.info("파일명 : " + f.getOriginalFilename());	//전송받은 파일명
			logger.info("파일 크기 : " + f.getSize() );
			logger.info("랜덤문자열 : "+UUID.randomUUID().toString());
			//서버에 저장할 파일객체를 생성합니다. : 생성자 인자는 파일객체의 경로와 파일명
//			File sfile = new File(path+"\\"+ f.getOriginalFilename());
			String temp = f.getOriginalFilename();
			String ext = temp.substring(temp.indexOf('.'),temp.length());	//확장자만 추출
			File sfile = new File(path+"\\"+UUID.randomUUID().toString().substring(0,8)+ext);
			//f.getOriginalFilename() 는 여러 접속자(사용자)가 중복된 이름을 사용할수 있다.
			//->파일명은 서버에서 임의로 정해줄것 입니다.
			try {
				//MultipartFile f 로 전송받은 파일을 참조하고 있으므로 
				if(f.getSize() != 0)
				f.transferTo(sfile);		//서버에 저장할 파일객체로
			} catch (IllegalStateException | IOException e) {
				logger.info("파일 전송오류 : " + e.getMessage());
				e.printStackTrace();
			} 
		}
	}
	
}









