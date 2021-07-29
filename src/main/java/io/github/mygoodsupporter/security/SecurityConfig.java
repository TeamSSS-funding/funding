package io.github.mygoodsupporter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberDetailsService memberDetailsService;


    public SecurityConfig(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/authentication").permitAll()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/oauth_kakao").permitAll()
                .antMatchers("/join").permitAll()
                .antMatchers("/idcheck").permitAll()
                .antMatchers("/projects").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/projects**").authenticated()
                .antMatchers("/projects/**").authenticated()
                .antMatchers("/users/member/**").hasRole("USER")
                .antMatchers("/users/admin/**").hasRole("ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("m_id")
                    .passwordParameter("m_password")
                .permitAll()
                .and()

                    .logout()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
