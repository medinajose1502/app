package actas.proyectolab2.app.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import actas.proyectolab2.app.servicios.SUserDetailsService;



@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter{
	
    //private AuthenticationSuccessHandler authenticationSuccessHandler;
    
/*    @Autowired
    public ConfiguracionSeguridadWeb(AuthenticationSuccessHandler authenticationSuccessHandler) 
    {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin().and()
            .httpBasic();
    }
    
    
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() 
    {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    SUserDetailsService userDetailsService;
	

    /*public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    */
}
