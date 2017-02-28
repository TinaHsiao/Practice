package Servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.UserBean;
import Service.UserManager;
import Util.CheckInput;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/user/accountTest.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	UserManager service = new UserManager();
	UserBean ub = new UserBean();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = request.getParameter("memberId");	
		
		HashMap<String,String> errorMessage = new HashMap<String,String>();
		request.setAttribute("errorMessage", errorMessage);
		request.setCharacterEncoding("UTF-8");
		if(!CheckInput.checkEmpty(request, errorMessage, "memberId", "帳號")){
			if(!service.checkMemberId(request.getParameter("memberId"))){
				errorMessage.put("memberId","帳戶不存在");
			}else{
				session.setAttribute("loginOK", "帳戶正確");
				session.setAttribute("memberId", memberId);
			}
		}
		
		if(errorMessage.isEmpty()){
			String contextPath = getServletContext().getContextPath();
			response.sendRedirect(response.encodeRedirectURL(contextPath +"/user/accountExist.jsp"));
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/user/accountExist.jsp");
			rd.forward(request, response);
		}
	}
	
	

}
