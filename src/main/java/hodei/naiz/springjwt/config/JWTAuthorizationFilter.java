package hodei.naiz.springjwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hodei.naiz.springjwt.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static hodei.naiz.springjwt.config.SecurityConstants.*;

/**
 * Created by Hodei Eceiza
 * Date: 5/23/2021
 * Time: 21:19
 * Project: springJWT
 * Copyright: MIT
 */

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserDetailsServiceImpl userDetailsService; //manually injected to get the authorities

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }
    //check the authorization header,if not present runs filter, if header is present run getAuthentication
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header=request.getHeader(HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication=getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
    //Read the JWT and uses the JWT to validate the token, then this new token is saved to SecurityContext
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token=request.getHeader(HEADER_STRING);
        if(token !=null){
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(token.replace(TOKEN_PREFIX,"")).getSubject();
            if(user!=null){
                UserDetails userDetails= userDetailsService.loadUserByUsername(user); //using userDetailsService to get the authority (AKA the role)
                return new UsernamePasswordAuthenticationToken(user,null, userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
