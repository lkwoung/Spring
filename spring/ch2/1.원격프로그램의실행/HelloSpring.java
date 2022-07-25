package com.lkwoung.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. ���� ȣ�Ⱑ���� ���α׷����� ���
@Controller
public class HelloSpring {
	
	int iv = 10;	// �ν��Ͻ� ����
	static int cv = 20;	// static ����
	
	// 2. URL�� �޼��带 ���� (hello��� URL�� ������ �ش��Լ��� ����)
	@RequestMapping("/hello")
	public void main() { // 4. static ���̵� �� ȣ��� (��Ĺ ���ο��� ��ü�� ������ ���� -> �ν��Ͻ� �ż���� ��밡��)
		// 3. �������� �ƴ� ��Ĺ �ֿܼ� ��� (���������� �Ⱥ������� �ܼ��� ���� �ش��ϴ� �Լ��� �� ����� ���� Ȯ���� �� ����.)
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
	private void mainPrivate() { // 6. private���� �ۼ��ص� �ܺο��� ȣ���� ����(URL�� ����), Ŭ���� �������� private -> main.java ���Ϸ� �̵��ؼ� Ȯ�� 
		System.out.println("Hello Private!");
		
		System.out.println(iv); // OK
		System.out.println(cv); // OK
	}
	
}

/*	5. static ��ſ� instance�� ���� ����.
 * 	-> static instance ������ ��� ����� �� ����.
 */
 
