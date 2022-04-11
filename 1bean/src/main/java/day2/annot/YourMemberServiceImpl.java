package day2.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import day2.review.MemberDao;
import day2.review.MemberService;
@Component(value = "yourService")	//bean id를 'yourService' 로 지정
public class YourMemberServiceImpl implements MemberService{
	@Autowired						//*MemberDao 구현체 bean이 1개일 때는 자동 주입
	@Qualifier("mybatisMemberDao")	//*MemberDao 구현체 bean이 2개이상일 때 bean이름 설정
	private MemberDao dao;			//*		ㄴ bean이름은 자동으로 클래스이름의 첫글자 소문자로 설정됩니다.
	//사용할수있는(의존관계 주입) 클래스를 MemberDao의 구현체로 합니다.
	//해당클래스는 Mybatis~Dao, Jdbc~Dao 클래스 2개입니다.
	
	//기본 생성자
	public YourMemberServiceImpl() {
		System.out.println("YourMemberServiceImpl create ////////////");
	}
	
	public void setDao(MemberDao dao) {	//의존관계주입 setter
		this.dao = dao;
	}
	
	@Override
	public void find(int idx) {
		//예, 검색 비즈니스 로직 처리 : Your 방식으로 
		System.out.println("Your Logic~~~~~~~~");
		dao.find(idx);
	}

}
