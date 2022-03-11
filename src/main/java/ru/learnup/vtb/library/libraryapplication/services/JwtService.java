package ru.learnup.vtb.library.libraryapplication.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author bse71
 * Created on 09.03.2022
 * @since
 */
@Service
public class JwtService {

    private static final long ACCESS_TOKEN_TIMEOUT = 60;
    private static final long REFRESH_TOKEN_TIMEOUT = 5 * 60;


    private final Algorithm jwtAlgorithm;
    private final Map<String, String> refreshToken = new HashMap<>();

    public JwtService(@Value("${config.auth.jwt.secret:jsfhmafkhmsjdhnkmsetph}") String jwtSecret) {
        this.jwtAlgorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    }

    public String generateAccessToken(UserDetails user) {
        final List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIMEOUT * 1000L))
                .withClaim("roles", roles)
                .sign(jwtAlgorithm);
    }

    public String generateRefreshToken(UserDetails user) {
        String rt = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIMEOUT * 1000L))
                .sign(jwtAlgorithm);
        refreshToken.put(user.getUsername(), rt);
        return rt;
    }

    public boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
            final DecodedJWT decodedJwt = verifier.verify(token);

            String username = decodedJwt.getSubject();
            String[] roles = decodedJwt.getClaim("roles").asArray(String.class);

            final List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authData =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authData);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;

    }

    public String getUsernameByRefreshToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
            final DecodedJWT decodedJwt = verifier.verify(token);
            final String username = decodedJwt.getSubject();

            final String refreshTokenInDb = refreshToken.get(username);

            return (token.equals(refreshTokenInDb)) ? username : null;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
