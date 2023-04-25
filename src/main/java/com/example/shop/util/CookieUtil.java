package com.example.shop.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface CookieUtil {

    static Optional<Cookie> getCookie(HttpServletRequest request, String cookieName) {
        return Optional.ofNullable(request.getCookies()).stream()
                .flatMap(Arrays::stream)
                .filter(e -> Objects.equals(e.getName(), cookieName))
                .findAny();
    }

    static Cookie createJwtCookie(String cookieName, String jwt) {
        Cookie jwtCookie = new Cookie(cookieName, jwt);
        if (cookieName.equals(JwtUtil.ACCESS_TOKEN)) {
            jwtCookie.setMaxAge(JwtUtil.ACEESS_TOKEN_EXPIRATION.intValue() / 1000);
        } else if (cookieName.equals(JwtUtil.REFRESH_TOKEN)) {
            jwtCookie.setMaxAge(JwtUtil.REFRESH_TOKEN_EXPIRATION.intValue() / 1000);
        }
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        return jwtCookie;
    }
}
