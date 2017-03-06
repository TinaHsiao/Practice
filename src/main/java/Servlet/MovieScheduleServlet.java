package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import Service.MovieScheduleManager;

/**
 * Servlet implementation class MovieScheduleServlet
 */
@WebServlet("/movieSchedule.search")
public class MovieScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	private MovieScheduleManager service = new MovieScheduleManager();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String room = request.getParameter("room");
		String date = request.getParameter("date");
		
		List<HashMap<String,String>> list = service.searchMovieSchedule(room,date);

		
//		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("movie_name", "測試1");
//		map.put("stime", "16:59");
//		map.put("etime", "19:48");
//		list.add(map);
//		map = new HashMap<String,String>();
//		map.put("movie_name", "測試2");
//		map.put("stime", "21:30");
//		map.put("etime", "00:49");
//		list.add(map);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append(new Gson().toJson(list));
	}

}
