package com.santander.challenge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("**/auth/**").permitAll()
                .antMatchers("/findAll/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "favicon.ico").permitAll()
                .antMatchers("/beerQty/**").hasAnyRole("ADMIN")
                .antMatchers("/invite/**").hasAnyRole("ADMIN")
                .antMatchers("/removeMeetUp/**").hasAnyRole("ADMIN")
                .antMatchers("/checkin/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/createMeetUp/**").hasAnyRole("ADMIN")
                .antMatchers("/confirmAssistence/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/obtainWeather/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/getUser/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN");
    }
}
