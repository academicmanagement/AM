package kr.inhatc.spring.user.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public class crawlingService {
	private static String noticeURL = "https://www.inhatc.ac.kr/kr/460/subview.do?enc=Zm5jdDF8QEB8JTJGY29tYkJicyUyRmtyJTJGMiUyRmxpc3QuZG8lM0ZmaW5kV29yZCUzRCUyNnBhZ2UlM0QxJTI2ZmluZFR5cGUlM0QlMjY%3D";
    
	@PostConstruct
	public List<Notice> getnotice() throws IOException{
		List<Notice> noticeList = new ArrayList<>();
		Document noticeDoc = Jsoup.connect(noticeURL).get();
		Elements contents = noticeDoc.select("table tbody tr");
		for(Element content : contents) {
			Elements tdContents = content.select("td");
			Notice notice = Notice.builder()
					.link(content.select("th").text())
					.num(tdContents.get(0).text())
					.subject(tdContents.get(1).text())
					.date(tdContents.get(2).text())
					.file(tdContents.get(3).text())
					.build();
			noticeList.add(notice);
		}
		return noticeList;
	}
}
