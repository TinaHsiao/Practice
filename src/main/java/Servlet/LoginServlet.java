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

@WebServlet("/user/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserManager service = new UserManager();
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//取出session物件
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的 Collection物件
		Map<String,String> errorMessage = new HashMap<String,String>();
		request.setAttribute("ErrorMsg", errorMessage);

		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		if(!CheckInput.checkEmpty(request, errorMessage, "memberId", "帳號")){
			if(!service.checkMemberId(request.getParameter("memberId"))){
				errorMessage.put("memberId", "帳號不存在");
			}
		}
		if(!CheckInput.checkEmpty(request, errorMessage, "password", "密碼")){
			if(!service.checkPassword(request.getParameter("memberId"),request.getParameter("password"))){
				errorMessage.put("password", "密碼錯誤");
			}else{
				session.setAttribute("loginOK", true);
			}
		}
		
		// 如果有錯誤，呼叫view元件，送回錯誤訊息
		if (errorMessage.isEmpty()) {
			String contextPath = getServletContext().getContextPath();
			String target = (String)session.getAttribute("target");
			if(target != null){
				session.removeAttribute("target");
				response.sendRedirect(response.encodeRedirectURL(contextPath+target));
			}else{
				response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp"));
			}			
			return;
		}else{			
			RequestDispatcher rd = request.getRequestDispatcher("/user/login.jsp");
			rd.forward(request, response);
		}
	}
}
