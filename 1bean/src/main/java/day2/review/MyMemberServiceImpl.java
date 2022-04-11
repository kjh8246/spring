package day2.review;

public class MyMemberServiceImpl implements MemberService{
	private MemberDao dao;
	//사용할수있는(의존관계 주입) 클래스를 MemberDao의 구현체로 합니다.
		//해당클래스는 Mybatis~Dao, Jdbc~Dao 클래스 2개입니다.
	
	//MyMemberServiceImple은 커스텀생성자 , 생성자 의존관계 주입으로 변경해서 해보세요.
	public MyMemberServiceImpl(MemberDao dao) {
		System.out.println("MyMemberServiceImpl create /////////");
		this.dao=dao;
	}	//빈설정파일의 빈 생성 내용 변경하세요.
	
//	public MyMemberServiceImpl() {
//		System.out.println("MyMemberServiceImpl create /////////");
//	}
	
	public void setDao(MemberDao dao) {	//의존관계주입 setter
		this.dao = dao;
	}
	
	@Override
	public void find(int idx) {
		// 비즈니스 로직 처리 : My 방식으로 
		System.out.println("My Logic~~~~~~");
		dao.find(idx);
	}
}
