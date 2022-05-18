package kr.inhatc.spring.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.inhatc.spring.user.entity.CourseVo;
import kr.inhatc.spring.user.service.CourseService;
import kr.inhatc.spring.user.utils.ScriptUtils;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	ScriptUtils scriptUtils;
	
	//강의등록
	@PostMapping("/signCourse")
	public String signCourse(HttpServletResponse response, CourseVo course) throws IOException {
		if(courseService.findByCode(course)) {
			scriptUtils.alertAndMovePage(response, "중복된 코드입니다!", "admin_course");
			
		}
		else {
			courseService.save(course);
		}
		return "redirect:/admin_course";
	}
	
	//강의 삭제
	@PostMapping("/deleteCourse")
	public String deleteCourse(Long crNo) throws Exception {
		try {
			courseService.deleteCourse(crNo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/admin_course";
	}
	
	//강의 리스트
	@GetMapping("/admin_course")
	public String list(Model model) {
		List<CourseVo> list = courseService.findAll();
		model.addAttribute("list", list);
		return "/admin_course";
	}
}
