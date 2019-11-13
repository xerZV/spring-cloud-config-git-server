package com.simitchiyski.cloud.config.server;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/international**")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui/index.html");
        web.ignoring().antMatchers("/international**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/material/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/public/**");
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http.csrf()
				.disable()
				.httpBasic();
        // @formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        cloud-server=$2a$10$ycWGIYfFlEIvxrbAMBsiXOMcN9b0d1/Ub9sgAADX7nYK/K1bfz256
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> mngConfig = auth.inMemoryAuthentication();
        mngConfig.withUser(User.withUsername("cloud-server").password("$2a$10$ycWGIYfFlEIvxrbAMBsiXOMcN9b0d1/Ub9sgAADX7nYK/K1bfz256").roles("USER").build());
    }
}
