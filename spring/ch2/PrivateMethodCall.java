package com.lkwoung.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		HelloSpring helloSpring = new HelloSpring();
//		hs.mainPrivate(); // private이라서 외부 호출 불가
		
		/**
		 * 웹 브라우저에서는 호출가능한 이유
		 * Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능제공
		 * java.lang.reflect패키지를 제공
		 * 
		 * Spring Framework이 이 API를 많이 사용한다.
		 * Spring이 reflection API를 사용해서 호출이 가능했던 것이였다.
		**/

		// HelloSpring클래스의 Class객체(클래스 정보를 담고 있는 객체)를 얻어온다.
		Class helloClass = Class.forName("com.lkwoung.ch2.HelloSpring");

		// Class객체가 가진 정보로 객체 생성, 반환형태가 object라서 형변환이 필요함
		HelloSpring helloSpring = (HelloSpring)helloClass.newInstance(); 
		
		// helloSpring 클래스의 mainPrivate매서드를 밖에서 호출하도록 바꿈
		Method mainPrivate = helloClass.getDeclaredMethod("mainPrivate");
		
		// private인 mainPrivate()을 호출 가능하도록 설정한다.
		mainPrivate.setAccessible(true);
		
		// hello.mainPrivate()을 호출한 것과 같음
		mainPrivate.invoke(helloSpring);
	}
}
