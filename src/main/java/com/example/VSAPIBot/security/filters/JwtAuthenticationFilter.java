package com.example.VSAPIBot.security.filters;

import com.example.VSAPIBot.security.authentication.JwtAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

// фильтр который первый запрос примет
// extend делает чтобы он мог быть бином
@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String value = request.getHeader("Auth");
        // создаем обхект
        // положили токен в аутентификацию
        Authentication authentication = new JwtAuthentication(value);
        // кладем его в securityContextHolder( привязываем к треду security Context)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // отправляем запрос дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
