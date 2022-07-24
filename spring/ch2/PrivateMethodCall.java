package com.lkwoung.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		HelloSpring helloSpring = new HelloSpring();
//		hs.mainPrivate(); // private�̶� �ܺ� ȣ�� �Ұ�
		
		/**
		 * �� ������������ ȣ�Ⱑ���� ����
		 * Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ �������
		 * java.lang.reflect��Ű���� ����
		 * 
		 * Spring Framework�� �� API�� ���� ����Ѵ�.
		 * Spring�� reflection API�� ����ؼ� ȣ���� �����ߴ� ���̿���.
		**/

		// HelloSpringŬ������ Class��ü(Ŭ���� ������ ��� �ִ� ��ü)�� ���´�.
		Class helloClass = Class.forName("com.lkwoung.ch2.HelloSpring");

		// Class��ü�� ���� ������ ��ü ����, ��ȯ���°� object�� ����ȯ�� �ʿ���
		HelloSpring helloSpring = (HelloSpring)helloClass.newInstance(); 
		
		// helloSpring Ŭ������ mainPrivate�ż��带 �ۿ��� ȣ���ϵ��� �ٲ�
		Method mainPrivate = helloClass.getDeclaredMethod("mainPrivate");
		
		// private�� mainPrivate()�� ȣ�� �����ϵ��� �����Ѵ�.
		mainPrivate.setAccessible(true);
		
		// hello.mainPrivate()�� ȣ���� �Ͱ� ����
		mainPrivate.invoke(helloSpring);
	}
}
