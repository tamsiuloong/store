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
		//ͳһת��
		request.setCharacterEncoding("utf-8");
		
		//��ȡmethod����
		String methodName = request.getParameter("method");
		if(methodName!=null&&!methodName.isEmpty())
		{
			//method = list
			//����:������д����ʱ������ȷ��Ҫ��ʼ���Ǹ��࣬Ҳ��֪��ִ�и���ʵ���Ǹ�����ʱʹ�÷��䡣
			//ִ�е�ǰ�����е� list����
			Class<?> clazz = this.getClass();
			try {
				Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				//ִ��this�����method����
				String result = (String) method.invoke(this, request,response);
				if(result!=null)
				{
					//��������ַ����а����ؼ���redirect:  �Ͳ����ض���
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
				request.setAttribute("msg", this.getClass().getName()+"��û���ҵ�"+methodName+"()����");
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
