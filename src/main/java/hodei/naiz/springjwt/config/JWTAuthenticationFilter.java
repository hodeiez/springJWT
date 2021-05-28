package hodei.naiz.springjwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static hodei.naiz.springjwt.config.SecurityConstants.EXPIRATION_TIME;
import static hodei.naiz.springjwt.config.SecurityConstants.SECRET;

/**
 * Created by Hodei Eceiza
 * Date: 5/23/2021
 * Time: 20:59
 * Project: springJWT
 * Copyright: MIT
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        setFilterProcessesUrl("/user/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            CustomUser creds=new ObjectMapper().readValue(req.getInputStream(), CustomUser.class);

            UserDetails userDetails=userDetailsService.loadUserByUsername(creds.getUsername()); //I needed the authorities, so I inject userDetails and use here
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),creds.getPassword(), userDetails.getAuthorities()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,Authentication auth) throws IOException{
        String token= JWT.create().withSubject(((User)auth.getPrincipal()).getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME)).sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body=((User) auth.getPrincipal()).getUsername() + " " + token;
        res.getWriter().write(body);
        res.getWriter().flush();
    }

}
