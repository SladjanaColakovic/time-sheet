package com.example.timesheet.app.security;

import com.example.timesheet.app.security.token.TokenUtils;
import com.example.timesheet.core.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String token = tokenUtils.getToken(request);
            UserInfo userInfo = new UserInfo(tokenUtils.getUserIdFromToken(token), tokenUtils.getRoleFromToken(token), tokenUtils.getUsernameFromToken(token));
            request.setAttribute("userInfo", userInfo);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
