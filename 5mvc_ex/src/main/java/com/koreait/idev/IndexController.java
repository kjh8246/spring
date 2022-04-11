package com.koreait.idev;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.idev.mapper.MemberMapper;
import com.koreait.idev.model.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("member")	//Model 저장소에 저장된 애트리뷰트 중에 member는 세션scope 값이라는 설정입니다.

public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	MemberMapper mapper;
	/*
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,@ModelAttribute("success") String success) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	@GetMapping("/login.do")
	//public String login(@ModelAttribute("success") String success) {
	public String login(String success,Model model) {
		model.addAttribute("success",success);	//url에 요청파라미터 남아있습니다.
		return "login";
	}
	
	@PostMapping("login.do")
	public String loginProc(@RequestParam Map<String, String> map, Model model,
			RedirectAttributes rdattr) {
		logger.info("[My]"+map);
		Member member = mapper.login(map);	//로그인 성공하면 null 아닌값 반환
		String url;
		if(member != null) {
			//성공 : 메인 화면으로 , session 객체에 로그인 정보를 저장했습니다.(세션 애트리뷰트로 저장하기)
			model.addAttribute("member",member);	//@SessionAttributes 로 설정하기
			model.addAttribute("success","y");
			url = "/";
			
		}else {//실패 : 다시 로그인 하러가기 .((미션)) alert 메시지 띄우기 "로그인 정보가올바르지 않습니다.!"
			//url = "login.do?success=n";
			//rdattr.addFlashAttribute("success","n");
			url = "login.do?success=n";
		}
		
			
		return "redirect:/"+url;
	}
	
	@GetMapping(".logout.do")
	public String logout() {
		return "redirect:/";
	}
	
}
