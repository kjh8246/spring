package com.koreait.idev.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

@Controller
@RequestMapping(value = "member/")	//Model 저장소에 저장한 에트리뷰트중에 member은 세션
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberMapper mapper;	//dao역할
	
	@GetMapping("/list.do")
	public String list(Model model) {	//매개변수로 선언된 Model 객체를 이용하여
										//지정된 view(또는 redirect url) 로 데이터를 전달합니다.
		List<Member> list = mapper.selectAll();
		model.addAttribute("list",list);		//view 에서는 el ${list} 로 데이터 가져오기
		return "member/MemberList";
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "member/MemberForm";
	}
	
	@PostMapping("/join.do")
	public String insert(Member member) {	//폼입력된 값의 name 속성과 Member객체가 매핑되어 
											//데이터가 저장됩니다.
		//response.setCharacterEncoding("UTF-8");
		logger.info("[My]"+member);
		mapper.addMember(member);
		return "redirect:../";
	}	//회원가입 
	
	@GetMapping("/update.do")
	public String update() {
		return "member/MemberUpdate";
	}
	
	@PostMapping("/save.do")
	public String save() {
		return "redirect:member/update.do";
	}
	
	@GetMapping("/idCheck.do")
	public String idCheck(String email,Model model) {	//검사하기 위한 email은 요청파라미터입니다.
		String msg;
		if(mapper.checkEmail(email)==0)
			msg="사용할수 있는 이메일 입니다";
		else 
			msg="사용할수 없는 이베일 입니다";
		model.addAttribute("email",email);
		model.addAttribute("msg",msg);
		return "member/idCheck";
	}
	
	
	
}
