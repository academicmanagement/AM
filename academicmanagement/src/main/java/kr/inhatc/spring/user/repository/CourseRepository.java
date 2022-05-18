package kr.inhatc.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.user.entity.CourseVo;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<CourseVo, Long>{

	public CourseVo findByCrcode(String crcode);	//코드 조회
}
