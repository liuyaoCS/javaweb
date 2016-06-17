package cn.ly.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class UserDao {
	private static Properties dbconfig=new Properties();
	static{
		InputStream in=UserDao.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			dbconfig.load(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update() {
		// TODO Auto-generated method stub
		String url=dbconfig.getProperty("url");
		System.out.println(url);
	}
	public String getUrl() {
		// TODO Auto-generated method stub
		String url=dbconfig.getProperty("url");
		return url;
	}

}
