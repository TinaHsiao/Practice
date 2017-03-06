package Servlet;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import Bean.UserBean;
import Dao.UserDAO;
import Service.UserManager;
import Util.CheckInput;
import Util.requestUtil;

@WebServlet("/user/user.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserManager service = new UserManager();
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		boolean isEmpty = false;
		if(memberId==null|| memberId.trim().length() == 0){
			isEmpty=true;
		}
		boolean checkExist = service.checkMemberId(memberId);
		String checkExistStr = "";
		if(checkExist == true){
			checkExistStr = "true";
		}else if(checkExist==false && isEmpty==true){
			checkExistStr = "error";
		}else{
			checkExistStr = "false";
		}
		response.getWriter().append(checkExistStr);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
				
		//取出session物件
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的 Collection物件
		Map<String,String> errorMessage = new HashMap<String,String>();
		request.setAttribute("ErrorMsg", errorMessage);

		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		if(!CheckInput.checkEmpty(request, errorMessage, "memberId", "帳號")){
			if(service.checkMemberId(request.getParameter("memberId"))){
				errorMessage.put("memberId", "帳號已經存在");
			}
		}
		CheckInput.checkEmpty(request, errorMessage, "password", "密碼");
		CheckInput.checkEmpty(request, errorMessage, "name", "姓名");
		CheckInput.checkEmpty(request, errorMessage, "email", "E-mail");
		CheckInput.checkEmpty(request, errorMessage, "address", "地址");
		CheckInput.checkEmpty(request, errorMessage, "phone", "電話");
		CheckInput.checkEmpty(request, errorMessage, "birthday", "生日");
		CheckInput.checkDate(request, errorMessage, "birthday", "生日");
		
		
		
		
		// 如果有錯誤，呼叫view元件，送回錯誤訊息
		if (!errorMessage.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/user/register.jsp");
			rd.forward(request, response);
			return;
		}else{
			UserBean bean = new UserBean();
			requestUtil.populate(request, bean);
			service.insertUserData(bean);
			
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(response.encodeRedirectURL(response.encodeRedirectURL(contextPath + "/index.jsp")));
		}
		
		
	}
}
