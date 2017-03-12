package Servlet;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import com.github.sarxos.webcam.Webcam;


/**
 * Servlet implementation class VideoServlet
 */
@WebServlet("/VideoServlet")
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*IP 攝影機(手機模擬)*/
//		response.setContentType("image/jpeg");
//		HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://192.168.0.104:8080/shot.jpg?username=sky&password=ray").openConnection();
//		urlConnection.setConnectTimeout(3500);
//		urlConnection.setReadTimeout(5000);
//		InputStream inputStream = urlConnection.getInputStream();
//		OutputStream out = new BufferedOutputStream(response.getOutputStream());
//		
//		byte[] buf = new byte[1024];
//		int count = 0;
//		 
//		while ((count = inputStream.read(buf)) >= 0) {
//		    out.write(buf, 0, count);
//		    out.flush();
//		}    
//		 
//		out.flush();
//		out.close();
/*電腦的視訊鏡頭*/
		ServletContext 權限控管Application = request.getServletContext();
		HttpSession 權限控管Session = request.getSession();
		
		/*其他地方會做的事情*/
		//當登入成功時可能會做的事情,一直都在即使跳頁 (15min or your setting)
//		權限控管Session.setAttribute("user", "黃大叔");
		權限控管Session.setAttribute("user", "張大媽");
		
		//今天輪到洗 張大媽 的車了~~~ (當時間到的時候,做的事情 ex:9:00)
		//Listener(ServletContext) 當啟動時那些時間要去做 
		//取得Application 的關鍵字 contextEvent.getServletContext();
		//排成的關鍵物件 ScheduledExecutorService
		//要做的事情? 到資料庫查詢目前正在處裡的使用者 將他寫入Application (如下)
		權限控管Application.setAttribute("user", null);
		權限控管Application.setAttribute("user", "張大媽");
		
		/*此地方要做的事情*/
		//判斷 Session 使用者是不是現在有權限看的人
		String session使用者名稱 = (String)(權限控管Session.getAttribute("user")==null?"None":權限控管Session.getAttribute("user"));
//		if(session使用者名稱.equals((String)權限控管Application.getAttribute("user"))){
//			System.out.println("攝影機要做的事");
//		}
		
		if(session使用者名稱.equals((String)權限控管Application.getAttribute("user"))){
			ServletContext application = request.getServletContext();
			Webcam webcam =null;
			if(null!=application.getAttribute("webcam")){
				webcam = (Webcam)application.getAttribute("webcam");
			}else{
				application.setAttribute("webcam",Webcam.getDefault());
				webcam = (Webcam)application.getAttribute("webcam");
				webcam.setViewSize(new Dimension(640, 480));
				webcam.open();
			}
			
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			ImageIO.write(webcam.getImage(), "jpg", out);
			out.flush();
			out.close();
		}else{
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("../../../images/carWash.jpg"));
			
			BufferedImage bimage = new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D bGr = bimage.createGraphics();
			bGr.drawImage(icon.getImage(), 0, 0, null);
			bGr.dispose();
			
			ImageIO.write(bimage, "jpg", out);
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
