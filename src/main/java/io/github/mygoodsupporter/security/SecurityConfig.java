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

    private final UserDetailsService userDetailsService;


    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/authentication").permitAll()
                .antMatchers("/", "/login/**").permitAll()
                .antMatchers("/joinPage").permitAll()
                .antMatchers("/join").permitAll()
                .antMatchers("/main.js").permitAll()
                .antMatchers("/idcheck").permitAll()
                .antMatchers("/projects").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/projects**").authenticated()
                .antMatchers("/projects/**").authenticated()
                .antMatchers("/profile/**").hasRole("USER")
                .antMatchers("/profile**").hasRole("USER")
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/users**").hasRole("USER")
                .antMatchers("/cards/**").hasRole("USER")
                .antMatchers("/cards**").hasRole("USER")
                .antMatchers("/payments/**").hasRole("USER")
                .antMatchers("/payments**").hasRole("USER")
                .antMatchers("/myPage").hasRole("USER")
                .antMatchers("/users/admin/**").hasRole("ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                    .logout()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
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
