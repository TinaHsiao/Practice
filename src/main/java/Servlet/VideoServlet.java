package Servlet;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
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
//		權限控管Application.setAttribute("user", null);
//		權限控管Application.setAttribute("user", "黃大叔");
		
		/*此地方要做的事情*/
		//判斷 Session 使用者是不是現在有權限看的人
		String session使用者名稱 = (String)權限控管Session.getAttribute("user");
		String 目前有權限的人 = (String)權限控管Application.getAttribute("user");
		
		// set the proper content type for MJPG
		//設定ContentType以及時串流(multipart)的方式
		response.setContentType("multipart/x-mixed-replace; boundary=--BoundaryString");
		OutputStream out = response.getOutputStream();
		/*電腦攝影機版本*/
//		ServletContext application = request.getServletContext();
//		Webcam webcam =null;
//		//視訊鏡頭如果存在application直接拿來用
//		if(null!=application.getAttribute("webcam")){
//			webcam = (Webcam)application.getAttribute("webcam");
//		//視訊鏡頭如果不存在application，則取得當下的視訊鏡頭處理
//		}else{
//			//Webcam.getDefault() 取得當下的鏡頭
//			application.setAttribute("webcam",Webcam.getDefault());
//			//將其寫入application 讓下次此Servlet被呼叫時 可以直接取來用
//			webcam = (Webcam)application.getAttribute("webcam");
//			//設置視訊鏡頭的解析度 640*480
//			webcam.setViewSize(new Dimension(640, 480));
//			//開啟視訊鏡頭
//			webcam.open();
//		}
//		while(session使用者名稱.equals(目前有權限的人)){
//			//因為我們要取得ByteArray固 new一個 ByteArrayOutputStream
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			//webcam.getImage()視訊鏡頭取得當前圖片 ，用jpg寫到 ByteArrayOutputStream baos 裡面
//			ImageIO.write(webcam.getImage(), "jpg", baos);
//			//寫入及時串流 out 為response的 OutputStream
//			if(!makeMJPEG(out,baos.toByteArray())){
//				break;
//			};
//			目前有權限的人 = (String)權限控管Application.getAttribute("user");
//		}
		
		/*IP攝影機 手機版本*/
		while(session使用者名稱.equals(目前有權限的人)){
			HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://192.168.0.106:8088/shot.jpg?username=sky&password=ray").openConnection();
			urlConnection.setConnectTimeout(3500);
			urlConnection.setReadTimeout(5000);
			InputStream inputStream = urlConnection.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(ImageIO.read(inputStream), "jpg", baos);
			if(!makeMJPEG(out,baos.toByteArray())){
				break;
			};
			目前有權限的人 = (String)權限控管Application.getAttribute("user");
		}
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("../../../images/carWash.jpg"));
		BufferedImage bimage = new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(icon.getImage(), 0, 0, null);
		bGr.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bimage, "jpg", baos);
		makeMJPEG(out,baos.toByteArray());
		out.close();
		
	}

	private boolean makeMJPEG(OutputStream outputStream,byte[] byteArray){
		try {
			// write the image and wrapper
			//將取得圖片的 byteArray 寫入當前的 response的 OutputStream
			outputStream.write((
				"--BoundaryString\r\n" +
				"Content-type: image/jpeg\r\n" +
				"Content-Length: " +
				byteArray.length +
				"\r\n\r\n").getBytes());
			outputStream.write(byteArray);
			outputStream.write("\r\n\r\n".getBytes());
			//將當前已寫入的OutputStream 先行傳送到網頁端
			outputStream.flush();			      

			// force sleep to not overwhelm the browser, simulate ~20 FPS
			// 圖片每隔多久傳送一次 越小影片會越順 但是網路流量會變大
			TimeUnit.MILLISECONDS.sleep(10);
			return true;
		}
		
		// If there is a problem with the connection (it likely closed), so return
		catch (Exception e) {
			return false;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
