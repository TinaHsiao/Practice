package Util;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import Bean.UserBean;

public class requestUtil {
	public static void populate(HttpServletRequest request,Object bean){
		
		HashMap map = new HashMap();
        Enumeration names = request.getParameterNames();

        while (names.hasMoreElements()){
          String name = (String) names.nextElement();
          map.put(name, request.getParameterValues(name));
        }

        try {
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
