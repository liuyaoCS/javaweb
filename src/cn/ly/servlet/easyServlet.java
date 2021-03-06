package cn.ly.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ly.dao.UserDao;

@WebServlet("/easyServlet")
public class easyServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getOutputStream().write("easyServlet".getBytes());
		
		//DaoTest(request, response);
		HttpSession session=request.getSession();
		String nameString=(String) session.getAttribute("name");
		response.getWriter().write("welcome:"+nameString);
	}

	private void DaoTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserDao ud=new UserDao();
		//ud.update();
		
		String data=ud.getUrl();
		this.getServletContext().setAttribute("data", data);
		
		RequestDispatcher rDispatcher=this.getServletContext().getRequestDispatcher("/index.jsp");
		rDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
