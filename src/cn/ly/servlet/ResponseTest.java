package cn.ly.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTest
 */
@WebServlet("/ResponseTest")
public class ResponseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//test1(response);
		//test2(response);
		//fileDownload(response);
		//randomImage(response);
		//autoRefresh(response);
		//controlCache(response);
		
		response.setStatus(302);
		response.setHeader("Location", "/JavawebTest/index.jsp");
		
	}

	private void controlCache(HttpServletResponse response) throws IOException {
		response.setDateHeader("expired", System.currentTimeMillis()+1000);
		String dateString="text";
		response.getWriter().write(dateString);
	}

	private void autoRefresh(HttpServletResponse response) throws IOException {
		response.setHeader("refresh", "20;url='/JavawebTest/index.jsp'");
		
		String dataS=new Random().nextInt(100)+"";
		response.getWriter().write(dataS+" jump after 20 second<a href='/JavawebTest/index.jsp'>link</a>");
	}

	private void randomImage(HttpServletResponse response) throws IOException {
		BufferedImage image=new BufferedImage(120, 25, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 120, 25);
		
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, 118, 23);
		
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, 120, 25);
		
		g.setColor(Color.RED);
		//g.setFont(new Font("宋体", Font.BOLD, 20)); // \u4e00-\u9fa5
		int i=new Random().nextInt()%10;
		g.drawString("312"+i, 20, 15);
		
		response.setHeader("content-type", "image/jpeg");
		response.setHeader("Cache-Control ", "no-cache");
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	private void fileDownload(HttpServletResponse response)
			throws FileNotFoundException, IOException {
		String path=this.getServletContext().getRealPath("/ic_launcher.png");
		String filenameString=path.substring(path.lastIndexOf("\\")+1);
		
		response.setHeader("content-disposition", "attachment;filename="+filenameString);//如果路径包含中文则需要url编码
		InputStream in=null;
		OutputStream out=null;
		in=new FileInputStream(path);
		int len=0;
		byte[] buffer=new byte[1024];
		out=response.getOutputStream();
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	private void test2(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");//writer直接打印string,没有机会改编码，用这种方式
		response.setHeader("Content-type", "text/html;charset=utf-8");//告诉浏览器以什么编码打开，同response.setContentType("text/html;charset=utf-8");
		String dataString="中国";
		PrintWriter writer=response.getWriter();
		writer.write(dataString);
	}

	private void test1(HttpServletResponse response) throws IOException{
		//也可以通过<meta>标签模拟http请求 <meta http-equiv='content-type' content='text/html;charset=utf-8' />	
		response.setHeader("Content-type", "text/html;charset=utf-8");
		
		String dataString="中国";
		OutputStream outputStream=response.getOutputStream();
		//outputStream.write(dataString.getBytes());
		outputStream.write(dataString.getBytes("utf-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
