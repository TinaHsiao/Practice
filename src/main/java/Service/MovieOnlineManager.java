package Service;

import java.util.HashMap;
import java.util.List;

import Dao.MovieOnlineDao;

public class MovieOnlineManager {
	
	private MovieOnlineDao dao = new MovieOnlineDao();

	public void movieAction(String action,String ptime,String movie,String roomid){
		if("A".equals(action)){
			dao.addMovieSchedule(ptime, Integer.parseInt(movie), roomid);
		}else if("C".equals(action)){
			dao.createSaleSeat(ptime, Integer.parseInt(movie), roomid);
		}else if("O".equals(action)){
			dao.movieOnline(ptime, Integer.parseInt(movie), roomid);
		}
	}
	
	public List<HashMap<String,String>> searchMovieList(){
		//List<HashMap<String,String>> list = dao.searchMovieIfo();
		return dao.searchMovieIfo();
	}
	
	public List<HashMap<String,String>> searchRoomList(){
		//List<HashMap<String,String>> list = dao.searchMovieIfo();
		return dao.searchRoom();
	}
}
