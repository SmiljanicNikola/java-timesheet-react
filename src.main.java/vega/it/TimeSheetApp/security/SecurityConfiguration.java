package vega.it.TimeSheetApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	static final String admin = "ADMIN";
	static final String worker = "WORKER";

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public JWTRequestFilter authenticationJwtTokenFilter() {
		return new JWTRequestFilter();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	   
   @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
  

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
  
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	
    	   httpSecurity.headers().cacheControl().disable();
           httpSecurity.cors();
           httpSecurity.headers().frameOptions().disable();
           httpSecurity.csrf().disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
			
            .antMatchers(HttpMethod.GET,"/api/categories").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.GET,"/api/categories/**").hasAnyRole(admin)
            .antMatchers(HttpMethod.DELETE, "/api/categories").hasRole(admin)
            .antMatchers(HttpMethod.POST, "/api/categories").hasRole(admin)
            .antMatchers(HttpMethod.PUT, "/api/categories/**").hasRole(admin)
    
            .antMatchers(HttpMethod.GET,"/api/projects").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.GET,"/api/projects/**").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.PUT,"/api/projects/**").hasRole(admin)
            .antMatchers(HttpMethod.DELETE,"/api/projects/**").hasRole(admin)
            .antMatchers(HttpMethod.POST,"/api/projects").hasRole(admin)
            
            .antMatchers(HttpMethod.GET,"/api/teamMembers").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.GET,"/api/teamMembers/**").hasAnyRole(admin, worker)
            .antMatchers(HttpMethod.PUT,"/api/teamMembers/**").hasRole(admin)
            .antMatchers(HttpMethod.DELETE,"/api/teamMembers/**").hasRole(admin)
            .antMatchers(HttpMethod.POST,"/api/teamMembers").hasRole(admin)
            
            .antMatchers(HttpMethod.GET,"/api/clients").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.GET,"/api/clients/**").hasAnyRole(admin,worker)
            .antMatchers(HttpMethod.PUT,"/api/clients/**").hasRole(admin)
            .antMatchers(HttpMethod.DELETE,"/api/clients/**").hasRole(admin)
            .antMatchers(HttpMethod.POST,"/api/clients").hasRole(admin)

            .antMatchers(HttpMethod.GET, "/api/timeSheetActivities").hasAnyRole(admin, worker)

        
           .anyRequest().authenticated();

           httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
           
        return httpSecurity.build();
    }



}
