package com.korea.dbapp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class sessionintercepter implements HandlerInterceptor {

    // 리턴이 true면 함수 진입, false면 진입 못함
    // preHandle에는 공통 관심사를 넣는다
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("principal") == null) {
            // 최초 요청이 들어온 uri로 있는게 아니라 redirect를 해주었다.
            response.sendRedirect("/auth/loginForm");
            return false;
        }

        return true;
    }
}
