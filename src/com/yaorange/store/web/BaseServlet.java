package com.yaorange.store.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaorange.store.entity.User;

public class BaseServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//统一转码
		request.setCharacterEncoding("utf-8");
		
		//获取method参数
		String methodName = request.getParameter("method");
		if(methodName!=null&&!methodName.isEmpty())
		{
			//method = list
			//反射:当你在写代码时，并不确定要初始化那个类，也不知道执行该类实例那个方法时使用反射。
			//执行当前对象中的 list方法
			Class<?> clazz = this.getClass();
			try {
				Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				//执行this对象的method方法
				String result = (String) method.invoke(this, request,response);
				if(result!=null)
				{
					//如果返回字符串中包含关键字redirect:  就采用重定向
					if(result.contains("redirect:"))
					{
						response.sendRedirect(result.replaceAll("redirect:", ""));
					}
					else
					{
						request.getRequestDispatcher(result).forward(request, response);
					}
					
				}
			} catch (NoSuchMethodException | SecurityException e) {
				request.setAttribute("msg", this.getClass().getName()+"中没有找到"+methodName+"()方法");
				request.getRequestDispatcher("/info.jsp").forward(request, response);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else
		{
			this.doGet(request, response);
		}
	}

	
	public String dosth(HttpServletRequest req,HttpServletResponse res)
	{
		return null;
	}


	public User getUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("user");
	}
}
