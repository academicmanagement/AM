package kr.inhatc.spring.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.user.entity.MemberVo;


@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long>{
	
	//long이 아니라 Long으로 작성
	//findBy 뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
	public List<MemberVo> findById(String id);
	public List<MemberVo> findByPw(String pw);
	public List<MemberVo> findByDept(String dept);
	public List<MemberVo> findByBan(String ban);
}
