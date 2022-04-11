package day4.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import day4.dto.BookRentDto;
import day4.mapper.BookRentMapper;

@Component
public class BookRentService {
	private BookRentMapper mapper;
	
	public BookRentService(BookRentMapper mapper) {
		this.mapper=mapper;
	}
	
	//트랜책션 : 하나의 세트(집합,그룹)로 실행되는 sql 명령들 특히 insert,update,delete
	//예시로 사나 -> 모모 계좌이체 (사나 잔액 - , 모모 잔액 + sql 명령실행)
	//2개 동시에 정상실행되어야 합니다. 그렇지 않고 하나라도 오류가 생기면 모두 취소
	
	//insert와 update를 트렌잭션으로 처리하기위한 어노테이션
	@Transactional
	public int insert(BookRentDto dto) {
		System.out.println("insert 전 dto rent_no : " + dto.getRent_no());
		int result = mapper.insert(dto);	//정상적으로 실행됨
		System.out.println("insert 후 dto rent_no : " + dto.getRent_no());
		mapper.updateExpDate(dto.getRent_no());		
		//update에 오류가 있으면 insert는 rollback
		return result;
	}
}
