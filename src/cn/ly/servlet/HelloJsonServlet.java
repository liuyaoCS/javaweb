package cn.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import cn.ly.dao.UserDao;

@WebServlet("/easyServlet")
public class HelloJsonServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//method1(response);
		
		method2(response);  
	
	}



	private void method2(HttpServletResponse response) throws IOException {
		//使用JSONArray测试  
        JSONArray jsonArray = new JSONArray();  
        jsonArray.add("MCA");  
        jsonArray.add("kevin");  
        jsonArray.add("15-12-1998");  
        jsonArray.add(new Double(12.3));  
        List<String> list = new ArrayList<String>();   
        list.add("a collection added");  
        list.add("kevin collection test");  
        jsonArray.addAll(list);  
          
        //页面输出JSONArray的内容  
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();  
        out.print(jsonArray);  
       
//        out.println("======================================");  
//        for(int i=0;i<jsonArray.size();i++){  
//            out.print(jsonArray.getString(i));  
//        }
	}



	private void method1(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String jsonStr = "{\"name\":\"fly\",\"type\":\"虫子\"}";
		
		try {
		    response.getWriter().write(jsonStr);
		 
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
