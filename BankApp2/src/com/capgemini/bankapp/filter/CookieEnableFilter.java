package com.capgemini.bankapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/login.do")
public class CookieEnableFilter implements Filter {

	public CookieEnableFilter() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("enableCookie.jsp");
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(req, res);
		}

	}
}

