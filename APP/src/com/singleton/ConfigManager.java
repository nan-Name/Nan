package com.singleton;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	//ʵ��
	private static ConfigManager configManger = new ConfigManager();
	//�����ļ�
	private static Properties properties;
	
	//����˽�еĹ�����
	private ConfigManager(){
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ȫ�ַ��ʵ�     �򵥵ĵ���ģʽ��������̲߳���ȫ����
	
	//����ģʽ
	public static ConfigManager getInstance(){
//		if(configManger == null){
//			configManger = new ConfigManager();
//		}
		return configManger;
	}
	
	//�������Եķ���
	public String getValue(String key){
		return properties.getProperty(key);
	}
}
