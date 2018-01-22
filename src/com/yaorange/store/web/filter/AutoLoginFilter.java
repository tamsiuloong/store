package com.yaorange.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.yaorange.store.entity.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter(description = "自动登录过滤器", urlPatterns = { "/*" })
public class AutoLoginFilter implements Filter {

	private UserService userService = new UserServiceImpl();

	public AutoLoginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 判断是否需要自动登录
		// 1，没有登录
		User user = (User) httpRequest.getSession().getAttribute("user");

		if (user == null) {
			// 2，cookie有autoLogin标识
			Cookie[] cookies = httpRequest.getCookies();
			if (cookies != null)
			{
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("autoLogin")) {
						// 表示需要自动登录
						// 从cookie中获取u/p
						String value = cookie.getValue();
						String[] up = value.split("&&&");
						String username = up[0];
						String password = up[1];

						// 根据u/p获取完整user对象
						user = userService.login(username, password);
						// 再将user对象保存到session
						httpRequest.getSession().setAttribute("user", user);
						break;// 跳出循环
					}
				}
			}
				
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
