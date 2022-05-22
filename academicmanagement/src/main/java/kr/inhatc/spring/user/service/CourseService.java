package kr.inhatc.spring.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user.entity.CourseVo;
import kr.inhatc.spring.user.repository.CourseRepository;
import kr.inhatc.spring.user.utils.ScriptUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	ScriptUtils scriptUtils;
	
	//모든 정보 검색
	public List<CourseVo> findAll(){
		List<CourseVo> course = new ArrayList<>();
		courseRepository.findAll().forEach(e -> course.add(e));
		return course;
	}
	
	//강의등록
	public CourseVo save(CourseVo course) {
		courseRepository.save(course);
		return course;
	}
	
	//강의 코드 찾기
	public boolean findByCode(CourseVo course) {
		try {
			CourseVo findCode = courseRepository.findByCrcode(course.getCrcode());
			if(findCode.getCrcode() != null) return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	//강의 삭제
	public void deleteCourse(Long crNo) {
		courseRepository.deleteById(crNo);
	}
	
	//선택 회원 삭제
	public void deleteAll(Long[] deleteId) {
		courseRepository.deleteCourse(deleteId);
	}
	
	//검색 - 페이징 처리
	public Page<CourseVo> list(String keyword, int page){
		
		//keyword가 없을 경우
		if(keyword.isEmpty()) {
			//RageRequest.of(우리가 보는 페이지, n개씩 페이징 처리, 내림차순 정렬)
			return courseRepository.findAll(PageRequest.of(page, 4, Sort.by(Sort.Direction.DESC, "crNo")));
		}
		else { //keyword가 있을 경우
			return courseRepository.findByCrcodeContaining(keyword, PageRequest.of(page, 4, Sort.by(Sort.Direction.DESC, "crNo")));
		}
	}
}
