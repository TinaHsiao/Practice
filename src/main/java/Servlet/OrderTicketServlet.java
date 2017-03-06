package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.MovieScheduleManager;

/**
 * Servlet implementation class OderTicketServlet
 */
@WebServlet("/OrderTicket.do")
public class OrderTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private  MovieScheduleManager service = new  MovieScheduleManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("orderTicketList", service.searchScheduleForOrder());
		String forwardPath = "/orderTicket.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("post");
		request.setAttribute("orderTicketList", service.searchScheduleForOrder());
		
//		List<HashMap<String,String>> list = service.searchScheduleForOrder();
//		request.setAttribute("movie_name", list.get(0).get("movie_name"));
//		request.setAttribute("pdate", list.get(1).get("pdate"));
//		request.setAttribute("stime", list.get(2).get("stime"));
//		request.setAttribute("etime", list.get(3).get("etime"));
//		request.setAttribute("roomid", list.get(4).get("roomid"));
		
		
		String forwardPath = "/orderTicket.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
		return;
	}

}
