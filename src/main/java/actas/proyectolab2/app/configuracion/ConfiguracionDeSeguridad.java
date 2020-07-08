package actas.proyectolab2.app.configuracion;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import actas.proyectolab2.app.servicios.SUserDetailsService;



@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter{
	
    @SuppressWarnings("unused")
	private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    public void ConfiguracionSeguridadWeb(AuthenticationSuccessHandler authenticationSuccessHandler) 
    {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ManejadorLogout();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).permitAll()
            .and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .and().csrf().disable()
            .httpBasic();
        
        http.cors().disable();
        http.headers().disable();
    }
    
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() 
    {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    SUserDetailsService userDetailsService;
	
    
    @SuppressWarnings("rawtypes")
	@Bean
    public FilterRegistrationBean simpleCorsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        CorsConfiguration config = new CorsConfiguration();  
        config.setAllowCredentials(true); 
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("*")); 
        config.setAllowedMethods(Collections.singletonList("*"));  
        config.setAllowedHeaders(Collections.singletonList("*"));  
        source.registerCorsConfiguration("/**", config);  
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
        return bean;  
    }
    

    /*public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    */
}
