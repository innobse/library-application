package ru.learnup.vtb.library.libraryapplication.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.learnup.vtb.library.libraryapplication.services.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description
 *
 * @author bse71
 * Created on 09.03.2022
 * @since
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    @Autowired
    public JwtAuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String servletPath = request.getServletPath();
        if (!servletPath.equals("/api/auth") && !servletPath.equals("/api/refresh")) {
            String token = getToken(request);
            if (token != null) {
                jwtService.verify(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    public static String getToken(HttpServletRequest request) {
        final String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (tokenHeader != null && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return tokenHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private static final String TOKEN_PREFIX = "Bearer ";
}
