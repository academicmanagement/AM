package kr.inhatc.spring.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="member")
@NoArgsConstructor	//디폴트 생성자 생성
@Data	//getter, setter 자동 생성
public class Member {

	@Id		//기본키 설정
	@Column(name="MEMEBER_ID")	//속성이름 설정
	private String memberId;
	
	@Column(name="MEMBER_PW")
	private String memberPw;
	
	@Column(name="MEMBER_NAME")
	private String memberName;
	
	@Column(name="MEMBER_DEPT")
	private String memberDept;
	
	@Column(name="MEMBER_CLASS")
	private String memberClass;
}
