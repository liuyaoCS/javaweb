package cn.ly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest
 */
@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sessionTest(request, response);
	}

	private void sessionTest(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session=request.getSession();
		
		String sessionId=session.getId();
		Cookie cookie=new Cookie("JSESSIONID",sessionId);
		cookie.setPath("/JavawebTest");
		cookie.setMaxAge(30*3600);
		response.addCookie(cookie);
		
		session.setAttribute("name", "liuyao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
