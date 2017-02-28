package Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.MovieOnlineManager;

/**
 * Servlet implementation class OrderTicketServlet
 */
@WebServlet("/OrderTicket.do")
public class OrderTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	MovieOnlineManager service = new MovieOnlineManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("playList", service.searchPlayList());
		String forwardPath = "/orderTicket.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		return;
	}
}
