package com.yaorange.store.web;

import com.alibaba.fastjson.JSON;
import com.yaorange.store.entity.User;
import com.yaorange.store.entity.vo.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonServlet extends HttpServlet {
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
				Result r = (Result) method.invoke(this, request,response);
				writeJson(r,response);
			} catch (Exception e) {
				e.printStackTrace();
				Result r = new Result();
				r.setOk(false);
				r.setMsg(e.getMessage());
				writeJson(r,response);
			}
		}
		else
		{
			this.doGet(request, response);
		}
	}

	
	public Result dosth(HttpServletRequest req,HttpServletResponse res)
	{
		return null;
	}
	protected void writeJson(String json,HttpServletResponse res) throws IOException {
		res.setContentType("text/javascript;charset=utf-8");
		res.getWriter().write(json);
		res.getWriter().flush();
	}

	protected void writeJson(Result r,HttpServletResponse res) throws IOException {
		res.setContentType("text/javascript;charset=utf-8");
		String json = JSON.toJSONString(r);
		res.getWriter().write(json);
		res.getWriter().flush();
	}
	public User getUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute("user");
	}
}
