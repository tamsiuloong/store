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
@WebFilter(description = "�Զ���¼������", urlPatterns = { "/*" })
public class AutoLoginFilter implements Filter {

	private UserService userService = new UserServiceImpl();

	public AutoLoginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// �ж��Ƿ���Ҫ�Զ���¼
		// 1��û�е�¼
		User user = (User) httpRequest.getSession().getAttribute("user");

		if (user == null) {
			// 2��cookie��autoLogin��ʶ
			Cookie[] cookies = httpRequest.getCookies();
			if (cookies != null)
			{
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("autoLogin")) {
						// ��ʾ��Ҫ�Զ���¼
						// ��cookie�л�ȡu/p
						String value = cookie.getValue();
						String[] up = value.split("&&&");
						String username = up[0];
						String password = up[1];

						// ����u/p��ȡ����user����
						user = userService.login(username, password);
						// �ٽ�user���󱣴浽session
						httpRequest.getSession().setAttribute("user", user);
						break;// ����ѭ��
					}
				}
			}
				
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
