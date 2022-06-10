package kr.inhatc.spring.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RvController {
	@Autowired
	private RvService rvService;
	
	@GetMapping("/cis_review")
	public String review(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<RvVo> course = rvService.findById(id);
		model.addAttribute("Course", course);
		
		return "/cis_review";
	}
	@PostMapping("/review_s")
	public String reviewSearch(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String crname = request.getParameter("crname");
	
		List<RvVo> course = rvService.findById(id);
		model.addAttribute("Course", course);
		
		List<RvVo> sReview = rvService.findByIdAndCrname(id, crname);
		model.addAttribute("Review", sReview);
		
		return "/cis_review";
	}
	@PostMapping("/review_update")
	public String updateReview(RvVo rvVo, Model model) throws Exception {
		rvService.updateById(rvVo.getRvNo(), rvVo);
		List<RvVo> course = rvService.findById(rvVo.getId());
		model.addAttribute("Course", course);
		
		return "/cis_review";
	}
}
