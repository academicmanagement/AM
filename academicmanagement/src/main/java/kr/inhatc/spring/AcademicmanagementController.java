package kr.inhatc.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AcademicmanagementController {
	
	@GetMapping("cis_main")
	public String hello() {
		return "cis_main";
	}
	@GetMapping("cis_schedule")
	public String schedule() {
		return "cis_schedule";
	}
	@GetMapping("cis_course")
	public String course() {
		return "cis_course";
	}
	@GetMapping("cis_review")
	public String review() {
		return "cis_review";
	}
	@GetMapping("cr_main")
	public String cr_main() {
		return "cr_main";
	}
	@GetMapping("cr_cancle")
	public String cr_cancle() {
		return "cr_cancle";
	}
	@GetMapping("cr_schedule")
	public String cr_schedule() {
		return "cr_schedule";
	}
	@GetMapping("login_cis")
	public String login_cis() {
		return "login_cis";
	}
	@GetMapping("login_cr")
	public String login_cr() {
		return "login_cr";
	}
	@GetMapping("login_mg")
	public String login_mg() {
		return "login_mg";
	}
	@GetMapping("admin_main")
	public String admin_main() {
		return "admin_main";
	}
	@GetMapping("admin_usercreate")
	public String admin_usercreate() {
		return "admin_usercreate";
	}
}
