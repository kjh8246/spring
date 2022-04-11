package day4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	private int mem_idx;
	private String name;
	private String email;	//unique, 로그인 계정
	private String tel;
	private String password;
}
