package kr.inhatc.spring.transcript;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.Privacy.PrVo;

@Controller
public class TranscriptController {
	@Autowired
	TranscriptService transcriptService;
	
	@GetMapping("/cis_reportcard")
	public String reportcard(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<TranscriptVo> y = transcriptService.findById(id);
		model.addAttribute("yeargradeterm", y);
		List<TranscriptVo> list = transcriptService.findById(id);
		model.addAttribute("transcript", list);
		
		return "/cis_reportcard";
	}
	@PostMapping("/s_transcript")
	public String searchtranscript(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String ygt = request.getParameter("ygt");
		
		List<TranscriptVo> y = transcriptService.findById(id);
		model.addAttribute("yeargradeterm", y);
		
		if(ygt.equals("전체")) {
			List<TranscriptVo> list = transcriptService.findById(id);
			model.addAttribute("transcript", list);
			return "/cis_reportcard";
		}else {
			List<TranscriptVo> list = transcriptService.findByIdAndYgt(id, ygt);
			model.addAttribute("transcript", list);
			System.out.println(ygt);
			return "/cis_reportcard";
		}
	}
}
