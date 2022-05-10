package kr.inhatc.spring.user.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class crawlingController {
	@GetMapping("/")
	public String crawling(Model model) throws Exception{
        
        String URL = "https://weather.naver.com/today";
        Document doc = Jsoup.connect(URL).get();
        Elements els1 = doc.select(".current");
        System.out.println(els1.text());
        Elements els2 = doc.select(".summary .weather");
        System.out.println(els2);
        
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
        System.out.println(weatherImg);
        
        return "/index";
	}
}
