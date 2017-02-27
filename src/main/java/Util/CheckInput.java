package Util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CheckInput {
	public static boolean checkEmpty(HttpServletRequest request,Map<String,String> errorMessage,String inputName,String show){
		boolean isEmpty = false; 
		String id = request.getParameter(inputName);
		// 檢查使用者所輸入的資料
		if (id == null || id.trim().length() == 0) {
			errorMessage.put(inputName,show+"欄必須輸入");
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public static void checkDate(HttpServletRequest request,Map<String,String> errorMessage,String inputName,String show){
		String day = request.getParameter(inputName);
		java.sql.Date date = null;
		if (day != null && day.trim().length() > 0) {
			try {
				date = java.sql.Date.valueOf(day);
			} catch (IllegalArgumentException e) {
				errorMessage.put(inputName,show+"欄格式錯誤");
			}
		}
	}
}
