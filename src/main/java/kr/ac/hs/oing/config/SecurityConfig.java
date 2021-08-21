package kr.ac.hs.oing.config;

import kr.ac.hs.oing.auth.AuthPrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthPrincipalService authPrincipalService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/index", "/", "/auth/**", "/js/**", "/css/**", "/image/**") // ::TODO 동진이껄로 변경해야함
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling();

        http
                .formLogin()
                .loginPage("/auth/login") // ::TODO 동진이껄로 변경해야함
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/auth/loginProc") // ::TODO 동진이껄로 변경해야함
                .defaultSuccessUrl("/") // ::TODO 동진이껄로 변경해야함
                .and()
                .exceptionHandling();

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ::TODO 동진이껄로 변경해야함
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling();

    }
}