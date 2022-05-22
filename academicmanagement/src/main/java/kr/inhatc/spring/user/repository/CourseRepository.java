package kr.inhatc.spring.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.user.entity.CourseVo;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<CourseVo, Long>{
	
	static final String DELETE_COURSE = "DELETE FROM COURSE " + "WHERE CR_NO IN (:deleteList)";

	//코드 조회
	public CourseVo findByCrcode(String crcode);
	
	//삭제 기능 sql
	@Transactional
	@Modifying
	@Query(value = DELETE_COURSE, nativeQuery = true)
	public void deleteCourse(@Param("deleteList") Long[] deleteList);
	
	//검색 기능 - Containing(Like 검색)
	public Page<CourseVo> findByCrcodeContaining(String crcode, Pageable pageable);
}
