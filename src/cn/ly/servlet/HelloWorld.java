package cn.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;
import cn.ly.bean.Product;

public class HelloWorld extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> products=new ArrayList<Product>();
		products.add(new Product(1,"tv",2000.0));
		products.add(new Product(2,"phone",1000.0));
		
		String json=JSONArray.fromObject(products).toString();
		//res.getOutputStream().write("hello servlet".getBytes());
		PrintWriter pw=res.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
	}

}
