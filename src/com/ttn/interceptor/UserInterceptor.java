package com.ttn.interceptor;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {
	
	//@Autowired
	

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	System.out.println("After completion");
	System.out.println(arg1.getHeader("X-AUTH-TOKEN") + " " + arg1.getStatus());
	System.out.println("Response body"+arg1.getContentType()+arg1.getHeader("Content-Type"));
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	//	arg1.setHeader("Access-Control-Expose-Headers", "X-Foo");
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("PreHandle" + arg0.getHeader("x-auth-token"));
//		System.out.println("Interceptor called");
//		System.out.println("Accept"+ arg0.getHeader("accept"));
//		System.out.println(arg0.getLocalAddr());
////		arg1.setContentType("application/json");
//		arg1.addHeader("x-auth-token" , "okay1");
		//arg1.addHeader("Content-Type", "application/json");
//		System.out.println(arg1.getHeader("Content-Type"));
//		System.out.println("All headers : " );
//		Enumeration<String> enumHeaders = arg0.getHeaderNames();
//		while(enumHeaders.hasMoreElements()){
//			System.out.println(enumHeaders.nextElement());
//		}
//		System.out.println("Host : "+arg0.getRemoteHost());
//		arg1.setHeader("Access-Control-Allow-Origin", "*");
	//	arg1.setHeader("Access-Control-Expose-Headers", "username");
         
		//arg1.setHeader("Access-Control-Allow-Headers", "username");
		
//		arg1.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
//        
//		arg1.setHeader("Access-Control-Allow-Headers", "Set-Cookie");


		return true;
	}

}
