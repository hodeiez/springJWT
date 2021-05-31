package hodei.naiz.springjwt.config;

import hodei.naiz.springjwt.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static hodei.naiz.springjwt.config.SecurityConstants.PUBLIC_URL;
import static hodei.naiz.springjwt.config.SecurityConstants.SIGN_UP_URL;

/**
 * Created by Hodei Eceiza
 * Date: 5/23/2021
 * Time: 21:30
 * Project: springJWT
 * Copyright: MIT
 */
@Configuration
@EnableWebSecurity
//@PropertySource("classpath:application-personal.properties")
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private Environment env;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public WebSecurity(UserDetailsServiceImpl userDetailsService, Environment env, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.env = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,SIGN_UP_URL).permitAll()//"open" urls with no authentication"
                .antMatchers(HttpMethod.GET,PUBLIC_URL).permitAll() //"open" urls with no authentication"
                .antMatchers(HttpMethod.GET,"/public/admin").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/public/user").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .oauth2Login().permitAll().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration=new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return  source;
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
               //.clientId("google-client-id")
               // .clientSecret("google-client-secret")
                .clientId(env.getProperty("spring.security.oauth2.registration.google.client-id"))
                .clientSecret(env.getProperty("spring.security.oauth2.registration.google.client-secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                //.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .redirectUri("http://localhost:8080/public") //now sends to a public page
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();
    }

}
