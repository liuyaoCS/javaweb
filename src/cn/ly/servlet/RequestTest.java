package cn.ly.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest
 */
@WebServlet("/RequestTest")
public class RequestTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//getCharsetHandler(request, response);
		requestDispatcher(request, response);

	}

	private void requestDispatcher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("data", "hello request dispatcher");
		//转发之前不能写入客户端数据，否则会报IllegalStateException
		//如果之前write写入数据,write.close没有调用,不会报异常，之前写入的数据会被清空，但是响应头数据保持
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void getCharsetHandler(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		String nameString=request.getParameter("username");
		nameString=new String(nameString.getBytes("iso8859-1"),"utf-8");
		
		if(nameString!=null && !nameString.trim().equals("")){
			response.setCharacterEncoding("utf-8");
			response.setHeader("content-type", "text/html;charset=utf-8");
			response.getWriter().write(nameString);
		}
	}

	private void postCharsetHandler(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		request.setCharacterEncoding("utf-8");//针对post请求,对于get: new String(data.getBytes("iso8859-1"),"utf-8")
		String nameString=request.getParameter("username");
//		if(nameString!=null && !nameString.trim().equals("")){
//			response.getWriter().write(nameString);
//		}
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().write(nameString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		postCharsetHandler(request, response);
	}

}
