package io.rulai.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {

	public static String getRemoteHost(HttpServletRequest request) {
		if (request != null) {
			// proxies do this
			String remote = request.getHeader("X-Forwarded-For");
			if (remote == null) {
				remote = request.getRemoteHost();
			}
		}
		return null;
	}

	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, long cookieExpiryTime) {
		int expInSeconds = (int) (cookieExpiryTime / 1000);
		if (request.getCookies() != null) {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(cookieName)) {
					c.setValue(cookieValue);
					c.setMaxAge(expInSeconds);
					c.setPath(request.getContextPath());
					response.addCookie(c);
					return;
				}
			}
		}
		Cookie c = new Cookie(cookieName, cookieValue);
		c.setMaxAge(expInSeconds);
		c.setPath(request.getContextPath());
		response.addCookie(c);
	}

}
