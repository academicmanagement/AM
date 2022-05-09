package kr.inhatc.spring.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import kr.inhatc.spring.user.constants.SessionConstants;
import kr.inhatc.spring.user.dto.MemberDTO;
import kr.inhatc.spring.user.entity.MemberVo;
import kr.inhatc.spring.user.repository.MemberRepository;
import kr.inhatc.spring.user.service.MemberService;
import kr.inhatc.spring.user.utils.ScriptUtils;

@Controller
public class MemberController {

	// 기본형
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MemberService memberService;
	
	ScriptUtils scriptUtils;

	// 모든 회원 조회
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<MemberVo>> getAllmembers() {
		List<MemberVo> member = memberService.findAll();
		return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
	}

	// 회원번호로 한명의 회원 조회
	@GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberVo> getMember(@PathVariable("mbrNo") Long mbrNo) {
		Optional<MemberVo> member = memberService.findById(mbrNo);
		return new ResponseEntity<MemberVo>(member.get(), HttpStatus.OK);
	}

	// 회원번호로 회원 삭제
	@DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
		memberService.deleteById(mbrNo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, pw, dept, ban 수정함)
	@PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberVo> updateMember(@PathVariable("mbrNo") Long mbrNo, MemberVo member) {
		memberService.updateById(mbrNo, member);
		return new ResponseEntity<MemberVo>(member, HttpStatus.OK);
	}
	// 로그인 체크
	@PostMapping("/signInCis")
	public String signInCis(HttpServletRequest request, HttpServletResponse response, MemberVo member) throws IOException {
		if(memberService.login(member)) {
			HttpSession session = request.getSession();
			session.setAttribute("id", member.getId());
			return "redirect:/cis_main";
		}
		scriptUtils.alertAndMovePage(response, "아이디 또는 비밀번호가 틀렸습니다!!", "/login_cis");
		return "/login_cis";
	}
	
	// 로그인 체크
		@PostMapping("/signInCr")
		public String signInCr(HttpServletResponse response,MemberVo member) throws IOException {
			if(memberService.login(member)) {
				return "redirect:/cr_main";
			}
			scriptUtils.alertAndMovePage(response, "아이디 또는 비밀번호가 틀렸습니다!!", "/login_cr");
			return "/login_cr";
		}
	
	// 회원가입
	@PostMapping("/signUp")
	public String joinMember(MemberVo member) {
		memberService.save(member);
		return "redirect:/";
	}
	// 로그아웃
	@PostMapping("/logoutbtn")
	public String logout(HttpServletRequest request) {

	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();   // 세션 날림
	    }

	    return "redirect:/";
	}
}
