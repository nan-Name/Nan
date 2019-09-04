package com.singleton;

//��̬�ಿ��
public class Singleton {
	private static Singleton singleton;
	
	//˽�й��캯��
	private Singleton(){
		//��������Ҫ���صĶ���
	}
	
	public static class SingletonHelper{
		private static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance(){
		singleton = SingletonHelper.INSTANCE;
		return singleton;
	}
	
	public static Singleton test(){
		return singleton;
	}
}
