package kr.inhatc.spring.user.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class Notice {
	private String link;
	private String num;
	private String subject;
	private String date;
	private String file;
}
