package Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.MovieOnlineManager;

/**
 * Servlet implementation class ProcessMemberServlet
 */
@WebServlet("/movieOnline.do")
public class MovieOnlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MovieOnlineManager service = new MovieOnlineManager();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String ptime = request.getParameter("ptime");
		String movie = request.getParameter("movie");
		String roomid = request.getParameter("roomid");
		
		service.movieAction(action,ptime,movie,roomid);
		request.setAttribute("action", action);
		request.setAttribute("ptime", ptime);
		request.setAttribute("movie", movie);
		request.setAttribute("roomid", roomid);
		
		request.setAttribute("movieList", service.searchMovieList());
		request.setAttribute("roomList", service.searchRoomList());
		
		String forwardPath = "/movieOnline.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		return;
	}

}
