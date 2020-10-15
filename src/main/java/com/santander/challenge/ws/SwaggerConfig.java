package com.santander.challenge.ws;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static java.util.Collections.singletonList;

/**
 * @author guillermovarelli
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(singletonList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .hidden(true)
                                .defaultValue("Bearer " + "....").required(false)
                                .build()
                        )
                )
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.santander.challenge.controller"))

                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(getMetadata());
    }
    private ApiInfo getMetadata() {
        ApiInfo apiInfo = new ApiInfo(
                "Challenge Santander",
                "Proyecto encargado de gestionar las meetUps de santander tecnologia.",
                "1.0.0",
                "",
                new Contact("Repositorio", "", ""),
                "",
                "", new ArrayList<>());
        return apiInfo;
    }

    protected void configure(HttpSecurity http)throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll() // This will be your home screen URL
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/auth**").permitAll()
                .antMatchers("**auth**").permitAll()
                .antMatchers("auth**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/postloginscreen") //configure screen after login success
                .loginPage("/auth")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

}
