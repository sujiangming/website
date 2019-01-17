package com.gnit.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器 查看用户是否登录,未登录禁止访问页面
 * 
 * @author lxj
 * @version 0.0.1
 *
 */
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI();

		HttpSession session = request.getSession(false);

		if (!"/index.jsp".equals(currentURL)) {// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
			if (session == null || session.getAttribute("user") == null) {
				// *用户登录以后需手动添加session
				//System.out.println("request.getContextPath()=" + request.getContextPath());
				response.sendRedirect(request.getContextPath() + "/login/login.jsp");
				// 如果session为空表示用户没有登录就重定向到login.jsp页面
				return;
			}
		}

		filterChain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}

}
