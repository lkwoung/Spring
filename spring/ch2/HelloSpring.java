package com.lkwoung.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출가능한 프로그램으로 등록
@Controller
public class HelloSpring {
	
	int iv = 10;	// 인스턴스 변수
	static int cv = 20;	// static 변수
	
	// 2. URL과 메서드를 연결 (hello라는 URL이 찍히면 해당함수가 실행)
	@RequestMapping("/hello")
	public void main() { // 4. static 없이도 잘 호출됨 (톰캣 내부에서 객체를 생성을 해줌 -> 인스턴스 매서드로 사용가능)
		// 3. 브라우저가 아닌 톰캣 콘솔에 출력 (브라우저에는 안보이지만 콘솔을 보면 해당하는 함수가 잘 실행된 것을 확인할 수 있음.)
		System.out.println("Hello Spring!");
		
		System.out.println(iv); // OK
		System.out.println(cv); // OK
	}
	
//	@RequestMapping("/hello")
	public static void mainStatic() {
		System.out.println("Hello Static!");
		
//		System.out.println(iv); // Error
		System.out.println(cv); // OK
	}
	
//	@RequestMapping("/hello")
	private void mainPrivate() { // 6. private으로 작성해도 외부에서 호출은 가능(URL과 맵핑), 클래스 내에서는 private -> main.java 파일로 이동해서 확인 
		System.out.println("Hello Private!");
		
		System.out.println(iv); // OK
		System.out.println(cv); // OK
	}
	
}

/*	5. static 대신에 instance가 좋은 이유.
 * 	-> static instance 변수를 모두 사용할 수 있음.
 */
 
