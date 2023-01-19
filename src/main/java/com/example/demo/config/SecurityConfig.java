package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.demoServiceImplementation.UserDetailServiceImpl;
import com.example.demo.filter.JWTFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	

    @Autowired
    private JWTFilter jwtFilter;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
   

    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(userDetailService);
			//.passwordEncoder(this.bCryptPasswordEncoder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	  @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.requiresChannel().anyRequest().requiresSecure()
	        .and()
	        .csrf().disable().authorizeRequests().antMatchers("/authenticate","/signUp","/teachers/**","/departments/**")
	                .permitAll().anyRequest().authenticated()
	                .and().exceptionHandling().and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	       // http.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    }
	
	
	

}
