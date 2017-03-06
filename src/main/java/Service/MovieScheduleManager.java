package Service;

import java.util.HashMap;
import java.util.List;

import Dao.MovieScheduleDAO;

public class MovieScheduleManager {

	MovieScheduleDAO dao = new MovieScheduleDAO();
	
	public List<HashMap<String,String>> searchMovieSchedule(String room,String date){
		return dao.searchMovieSchedule(room,date);
	}
	
	public List<HashMap<String,String>> searchScheduleForOrder(){
		return dao.searchScheduleForOrder();
	}
}
