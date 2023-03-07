package egovframework.example.cmmn.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
	
    private static final String[] AUTH_WHITELIST = {
    		"/css/**"
    		, "/js/**"
    		, "/img/**"
    };
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    	.csrf()
	        .disable()
	    .authorizeRequests()
	        .antMatchers("/login.do")
	    		.permitAll() 
	    	.anyRequest()
	        	.authenticated()
	    .and() 
	    	.formLogin()
		.and()
			.logout() // 8
			.invalidateHttpSession(true)
			;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
    
    @Bean
    protected CustomAuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}

}
