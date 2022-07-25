package com.lkwoung.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// ctrl + shift + o : 자동임포트


@Controller
public class TwoDice {
	
	// 해당하는 url에 요청을 받으면
	@RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws IOException {
		int idx1 = (int)(Math.random()*6)+1;
		int idx2 = (int)(Math.random()*6)+1;
		
		
		response.setContentType("text");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src='resources/img/dice"+idx1+".jpg'>");
		out.println("<img src='resources/img/dice"+idx2+".jpg'>");
		out.println("</body>");
		out.println("</html>");
	}

}

//동적으로 프로그램을 돌려서 결과를 주면된다.
// 실행할 때마다 결과가 달라지는 프로그램을 동적 리소스라고 한다. (프로그램, 스트리밍)
// 이미지 파일같은 것은 정적 리소스라고 한다. (파일 형태의 바뀌지 않는 것 .jpg, js, css, html)