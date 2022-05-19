package kr.inhatc.spring.Privacy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrController {
	PrService prService;
	
	@GetMapping("/cis_myinfo")
	public String findById(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("접속중인 ID: "+id);
		return "/cis_myinfo";
	}
}
