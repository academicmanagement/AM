package kr.inhatc.spring.user.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.spring.user.service.Notice;
import kr.inhatc.spring.user.service.crawlingService;

@Controller
public class crawlingController {
	@Autowired
	crawlingService cs;
	@GetMapping("/")
	public String crawling(Model model) throws Exception{
        
        /*String URL = "https://weather.naver.com/today";
        Document doc = Jsoup.connect(URL).get();
        Elements els1 = doc.select(".current");
        //System.out.println(els1.text());
        Elements els2 = doc.select(".summary .weather");
        //System.out.println(els2);*/
		String URL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%9D%B8%EC%B2%9C+%EB%AF%B8%EC%B6%94%ED%99%80%EA%B5%AC+%ED%95%99%EC%9D%B5+1%EB%8F%99+%EB%82%A0%EC%94%A8&oquery=%EC%9D%B8%EC%B2%9C+%EB%AF%B8%EC%B6%94%ED%99%80%EA%B5%AC+%ED%95%99%EC%9D%B5+1%EB%8F%99%EB%82%A0%EC%94%A8&tqi=hFxVnwprvN8ssfn337lssssss6C-334548";
        Document doc = Jsoup.connect(URL).get();
        Elements els1 = doc.select("._today .temperature_text");
        System.out.println(els1.text());
        Elements els2 = doc.select(".summary .weather");
        System.out.println(els2.text());
        
        String tels = els1.text();
        tels = tels.replace("현재 온도", " ");
        model.addAttribute("weather", els2.text());
        model.addAttribute("temperature", tels);
        
        String we = els2.text();
        String weatherImg = "img/weather_anoter.png";
        
        if(we.equals("맑음")) {
        	weatherImg = "img/weather_sunny.png";
        }else if(we.equals("구름조금")) {
        	weatherImg = "img/weather_cloudy.png";
        }else if(we.equals("구름많음")) {
        	weatherImg = "img/weather_cloudy.png";
        }else if(we.equals("흐림")) {
        	weatherImg = "img/weather_cloudy.png";
        }else if(we.equals("약한비")) {
        	weatherImg = "img/weather_rainy.png";
        }else if(we.equals("비")) {
        	weatherImg = "img/weather_rainy.png";
        }else if(we.equals("강한비")) {
        	weatherImg = "img/weather_shower.png";
        }else if(we.equals("약한눈")) {
        	weatherImg = "img/weather_snow.png";
        }else if(we.equals("강한눈")) {
        	weatherImg = "img/weather_snow.png";
        }else if(we.equals("진눈깨비")) {
        	weatherImg = "img/weather_snow.png";
        }else if(we.equals("소나기")) {
        	weatherImg = "img/weather_shower.png";
        }else if(we.equals("소낙눈")) {
        	weatherImg = "img/weather_snow.png";
        }else if(we.equals("안개")) {
        	weatherImg = "img/weather_fog.png";
        }else{
        	weatherImg = "img/weather_anoter.png";
        }
        model.addAttribute("wimg", weatherImg);
        
        List<Notice> noticeList = cs.getnotice();
        model.addAttribute("noticestat", noticeList);
        
        return "/index";
	}
}
