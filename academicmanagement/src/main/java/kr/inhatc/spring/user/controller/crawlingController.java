package kr.inhatc.spring.user.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class crawlingController {
	@RequestMapping(value="/")
	public String crawling(Model model) throws Exception{
		String URL = "https://weather.naver.com/today";
        Document doc = Jsoup.connect(URL).get();
        Elements elem = doc.select(".summary .weather");
        String[] str = elem.text().split(" ");
        Elements elem2=doc.select(".summary_img .ico_animation _cnLazy ico_animation_wt1");
 
        model.addAttribute("test1", elem);
        model.addAttribute("test2", elem2);
        
        return "1";
	}
}
