package ru.learnup.vtb.library.libraryapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnup.vtb.library.libraryapplication.services.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.learnup.vtb.library.libraryapplication.filters.JwtAuthorizationFilter.getToken;

/**
 * Description
 *
 * @author bse71
 * Created on 09.03.2022
 * @since
 */
@RestController
public class TokenController {

    private JwtService jwtService;
    private UserDetailsService userService;

    @Autowired
    public TokenController(JwtService jwtService, UserDetailsService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/api/refresh")
    public void authByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        String username = null;

        if (token != null) {
            username = jwtService.getUsernameByRefreshToken(token);
            if (username != null) {
                final UserDetails user = userService.loadUserByUsername(username);
                if (user != null) {
                    final String accessToken = jwtService.generateAccessToken(user);
                    final String refreshToken = jwtService.generateRefreshToken(user);
                    response.setHeader("access_token", accessToken);
                    response.setHeader("refresh_token", refreshToken);
                }
            } else {
                response.setStatus(401);
            }
        }
    }


}
