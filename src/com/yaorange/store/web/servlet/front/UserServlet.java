package com.yaorange.store.web.servlet.front;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.yaorange.store.entity.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.UserServiceImpl;
import com.yaorange.store.utils.MyDateConverter;
import com.yaorange.store.web.BaseServlet;

@WebServlet("/user.do")
public class UserServlet extends BaseServlet {

	private UserService userService = new UserServiceImpl();

	public String checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取参数
		String username = request.getParameter("username");
		// 查询用户名是否存在
		Boolean isExist = userService.checkUsername(username);
		// 通过response直接打印结果
		response.getWriter().write(isExist.toString());
		response.getWriter().flush();
		return null;
	}

	public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 判断验证码
		String yzmCode = request.getParameter("yzmCode");

		String sessionCode = (String) request.getSession().getAttribute("code");
		if (yzmCode != null && !yzmCode.isEmpty() && yzmCode.equalsIgnoreCase(sessionCode)) {
			// 将表单数据封装到user对象中
			User user = new User();
			try {
				ConvertUtils.register(new MyDateConverter(), Date.class);
				BeanUtils.populate(user, request.getParameterMap());

				boolean result = userService.register(user);
				if (result) {
					// 请求转发到提示页面
					request.setAttribute("msg", "注册成功，请求到" + user.getEmail() + "中去激活账号.");
					return "info.jsp";
				}

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("msg", "验证码不正确!");
			return "register.jsp";
		}

		return null;
	}

	public String active(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		boolean active = userService.active(code);

		if (active) {
			// 激活成功
			// 跳转登录页面
			return "login.jsp";
		} else {
			//提示
			request.setAttribute("msg", "激活失败，激活码无效！");
			return "info.jsp";
		}
	}

	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String yzmCode = request.getParameter("yzmCode");
		String autoLogin = request.getParameter("autoLogin");
		String remeberMe = request.getParameter("remeberMe");
		
		String sessionCode = (String) request.getSession().getAttribute("code");
		if(yzmCode!=null && !yzmCode.isEmpty() && yzmCode.equalsIgnoreCase(sessionCode))
		{
			User user = userService.login(username,password);
			if(user!=null)
			{
				//登录成功，将user保存到session
				request.getSession().setAttribute("user", user);
				
				//判断是否需要自动登录
				if(autoLogin!=null)
				{
					Cookie alCookie = new Cookie("autoLogin", username+"&&&"+password);
					alCookie.setMaxAge(60*60*24*7);
					response.addCookie(alCookie);
				}
				
				//判断是否需要记住用户名
				if(remeberMe!=null)
				{
					Cookie rmCookie = new Cookie("remeberMe", username);
					rmCookie.setMaxAge(60*60*24*7);
					response.addCookie(rmCookie);
				}
				return "redirect:index.html";
			}
			else
			{
				request.setAttribute("msg", "用户名，密码错误!");
			}
		}
		else
		{
			request.setAttribute("msg", "验证码错误");
		}
		return "login.html";
		
		
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//注销session，cookie
		request.getSession().invalidate();
		
		Cookie alCookie = new Cookie("autoLogin",null);
		alCookie.setMaxAge(0);
		response.addCookie(alCookie);
		
		Cookie rmCookie = new Cookie("remeberMe", null);
		rmCookie.setMaxAge(0);
		response.addCookie(rmCookie);
		
		return "login.jsp";
		
	}
	
}
