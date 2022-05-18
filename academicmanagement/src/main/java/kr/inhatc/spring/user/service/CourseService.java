package kr.inhatc.spring.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
