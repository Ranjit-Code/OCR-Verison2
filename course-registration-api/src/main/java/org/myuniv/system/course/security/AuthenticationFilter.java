package org.myuniv.system.course.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.myuniv.system.course.beans.dto.UserDto;
import org.myuniv.system.course.beans.requests.UserRequest;
import org.myuniv.system.course.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Environment  environment;
    private UsersService usersService;

    @Autowired public AuthenticationFilter(UsersService usersService, Environment environment,
        AuthenticationManager authenticationManager, CustomAuthenticationFailureHandler failureHandler) {
        this.environment = environment;
        this.usersService = usersService;
        super.setAuthenticationManager(authenticationManager);
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {

        UserRequest credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
        } catch (IOException e) {
            System.out.println("Error while attempting authentication: " + e.getMessage());
        }
        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(),
                new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDetails = usersService.getUserDetailsByEmail(userName);

        String token = Jwts.builder().setSubject(userDetails.getUserId()).setExpiration(
            new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
            .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"expiresIn\":\"3600\",\"token\":\"" + token + "\"}");
        out.flush();
    }

    @Override protected AuthenticationFailureHandler getFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
