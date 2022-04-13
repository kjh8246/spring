package com.mycompany.idev.mapper;

import java.util.List;
import java.util.Map;

import com.mycompany.idev.dto.Member;

public interface MemberMapper {
	int getCount();
	List<Member> selectAll();
	Member selectByMon(int mno);
	Member login(Map<String,String> map);
	void changePassw(Map<String, String> map);
	int checkEmail(String email);
	int addMember(Member member);
	int updateMember(Member member);
}
