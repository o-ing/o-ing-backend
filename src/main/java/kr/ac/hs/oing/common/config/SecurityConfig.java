package kr.ac.hs.oing.common.config;

import java.util.Arrays;
import kr.ac.hs.oing.auth.JwtAccessDeniedHandler;
import kr.ac.hs.oing.auth.JwtAuthenticationEntryPoint;
import kr.ac.hs.oing.auth.infrastructure.JwtSecurityConfig;
import kr.ac.hs.oing.auth.infrastructure.JwtTokenProvider;
import kr.ac.hs.oing.common.property.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CORS_SPLIT_REGEX = ",";
    private static final String CORS_ALL_URL = "/**";

    private final CorsProperties corsProperties;
    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/favicon.ico", "/error", "/api/clubs");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors().configurationSource(corsConfigurationSource());

        httpSecurity
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler);

        httpSecurity
            .headers()
            .frameOptions()
            .sameOrigin();

        httpSecurity
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers("/api/member/login", "/api/member/sign", "/api/clubs", "/api/club/{id}")
            .permitAll()
            .anyRequest().authenticated();

        httpSecurity
            .apply(jwtSecurityConfig());
    }

    private JwtSecurityConfig jwtSecurityConfig() {
        return new JwtSecurityConfig(tokenProvider);
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(
            Arrays.asList(corsProperties.getAllowedHeaders().split(CORS_SPLIT_REGEX))
        );
        corsConfig.setAllowedMethods(
            Arrays.asList(corsProperties.getAllowedMethods().split(CORS_SPLIT_REGEX))
        );
        corsConfig.setAllowedOrigins(
            Arrays.asList(corsProperties.getAllowedOrigins().split(CORS_SPLIT_REGEX))
        );

        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration(CORS_ALL_URL, corsConfig);
        return corsConfigSource;
    }
}