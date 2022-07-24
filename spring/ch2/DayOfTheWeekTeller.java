package com.lkwoung.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DayOfTheWeekTeller {

	// 년월일을 입력하면 요일을 알려주는 프로그램
	@RequestMapping("/getYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 1.입력
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// 2.작업
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=일요일 2=월요일 ...
		char whatDay = " 일월화수목금토".charAt(dayOfTheWeek);
		
		// 3.출력
		// 브라우저에 보내는 내용의 형식을 설정 (텍스트, 바이너리형식과 인코딩 형식)
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter(); // response 객체에서 브라우저로의 출력 스트림을 얻는다.
		
		out.println(year + "년 " + month + "월 " + day+ "일은 ");
		out.println(whatDay + "요일입니다.");
		
		System.out.println(year + "년 " + month + "월 " + day+ "일은 ");
		System.out.println(whatDay + "요일입니다.");

	}

}
