package br.com.status.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
//	@Autowired
//	private Properties properties;
	
	@GetMapping("ok")
	public ResponseEntity<String> ok() {
		
		return ResponseEntity.ok("OK");
	}
	
	@DeleteMapping("/tokens/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Usuario {} efetuando LOGOUT", req.getUserPrincipal().getName());
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
//		cookie.setSecure(properties.getSeguranca().isEnableHttps());
//		cookie.setPath(req.getContextPath() + properties.getCookiePath() + "/oauth/token");
		cookie.setMaxAge(0);

		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

}
