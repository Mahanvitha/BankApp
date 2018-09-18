package com.capgemini.bankapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PerformanceCheckFilter
 */
@WebFilter("/*")
public class PerformanceCheckFilter implements Filter {
	private ServletContext context;
    /**
     * Default constructor. 
     */
    public PerformanceCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		// pass the request along the filter chain
		long enteringTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		long exitTime = System.currentTimeMillis();
//		System.out.println(e-g);
		context.log("Time Taken " + (exitTime-enteringTime) +" for servlet "+ req.getServletPath());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		context = fConfig.getServletContext();
	}
}
