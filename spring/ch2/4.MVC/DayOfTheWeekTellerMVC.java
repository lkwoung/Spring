package com.lkwoung.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 1. 리턴값으로 JSP와 매핑 -> 이게 일반적으로 사용
//@Controller
//public class DayOfTheWeekTellerMVC {
//
//	@RequestMapping("/getYoilMVC")
//	 public String main(int year, int month, int day, Model model) throws IOException {
//		
//		1. 유효성 검사
//		if(!isValid(year, month, day))
//			return "yoilError";  
//		 
//		// 2. 요일 계산
//		char yoil = getYoil(year, month, day);
//		
//		// 3. 계산 결과를 model에 저장
//		model.addAttribute("year", year);
//		model.addAttribute("month", month);
//		model.addAttribute("day", day);
//		model.addAttribute("yoil", yoil);
//		
//		return "yoil"; //WEB-INF/views/yoil.jsp
//		  
//
//	}
//
//	private boolean isValid(int year, int month, int day) {
//		return true;
//	}
//
//	private char getYoil(int year, int month, int day) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(year, month-1, day);
//		
//		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=일요일 2=월요일 ...
//		return " 일월화수목금토".charAt(dayOfTheWeek);
//	}
//
//}

// 2. 뷰와 URL의 이름으로 맵핑
//@Controller
//public class DayOfTheWeekTellerMVC {
//	/**
//	 * 반환타입을 void로 하고 return yoil없이도 서로 연결할 수 있다. (뷰와 url의 이름이 같다면)
//	 * 매핑된 /getYoilMVC의 JSP파일을 만드는 것이다.
//	 * 그러면 getYoilMVC의 이름의 JSP가 웹 브라우저에 출력된다.
//	 */
//	@RequestMapping("/getYoilMVC")
//	public void main(int year, int month, int day, Model model) throws IOException {
//		
//		char yoil = getYoil(year, month, day);
//		
//		// 3. 계산 결과를 model에 저장
//		model.addAttribute("year", year);
//		model.addAttribute("month", month);
//		model.addAttribute("day", day);
//		model.addAttribute("yoil", yoil);		
//	}
//	private char getYoil(int year, int month, int day) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(year, month-1, day);
//		
//		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=일요일 2=월요일 ...
//		return " 일월화수목금토".charAt(dayOfTheWeek);
//	}
//}

// 3. ModelAndView로 매핑
@Controller
public class DayOfTheWeekTellerMVC {
	@RequestMapping("/getYoilMVC")
	 public ModelAndView main(int year, int month, int day) throws IOException {
		
		// 모델 생성
		ModelAndView mv = new ModelAndView(); // dispatcherServlet에서 생성을 하지않고 매서드(컨트롤러) 안에서 생성 
		
		char yoil = getYoil(year, month, day);

		// 결과 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		// 1. 결과를 보여줄 view를 지정 (매서드는 하나의 값만 반환하지만 여기서는 모델과 뷰를 같이 반환하기 위해서) 
		mv.setViewName("yoil");
		return mv;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=일요일 2=월요일 ...
		return " 일월화수목금토".charAt(dayOfTheWeek);
	}

}
