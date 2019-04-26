package br.com.status.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenPreProcessor implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if ((req.getContextPath() + "/oauth/token").equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equals(req.getParameter("grant_type"))
				&& req.getCookies() != null)
			for (Cookie cookie : req.getCookies())
				if (cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin" , "http://localhost:4200" ); 
		res.setHeader("Access-Control-Allow-Methods" , "POST, PUT, GET, OPTIONS, DELETE" ); 
		res.setHeader("Access-Control-Allow-Headers" , "Authorization, Content-Type" );
		res.setHeader("Access-Control-Allow-Credentials" , "true" );
		res.setHeader("Access-Control-Max-Age" , "3600");
		if ("OPTIONS".equals(req.getMethod()))
			res.setStatus(HttpServletResponse.SC_OK);
		else 
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) {

	}

	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[]{refreshToken});
			map.setLocked(true);

			return map;
		}
	}
}
