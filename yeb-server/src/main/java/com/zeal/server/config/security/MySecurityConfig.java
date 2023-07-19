package com.zeal.server.config.security;

import com.zeal.server.config.filter.CustomFilter;
import com.zeal.server.config.filter.CustomUrlDecisionManager;
import com.zeal.server.config.filter.JwtAuthenticationTokenFilter;
import com.zeal.server.service.impl.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/12 11:03
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsServiceImpl myUserDetailsService;
    private final RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
//    private final CustomFilter customFilter;
//    private final CustomUrlDecisionManager customUrlDecisionManager;


    public MySecurityConfig(MyUserDetailsServiceImpl myUserDetailsService, RestAuthorizationEntryPoint restAuthorizationEntryPoint, RestfulAccessDeniedHandler restfulAccessDeniedHandler, PasswordEncoder passwordEncoder, CustomFilter customFilter, CustomUrlDecisionManager customUrlDecisionManager) {
        this.myUserDetailsService = myUserDetailsService;
        this.restAuthorizationEntryPoint = restAuthorizationEntryPoint;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.passwordEncoder = passwordEncoder;
//        this.customFilter = customFilter;
//        this.customUrlDecisionManager = customUrlDecisionManager;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(
                        "/login/doLogin",
                        "/login/doLogout",
                        "/css/**",
                        "/js/**",
                        "/index.html",
                        "favicon.ico",
                        "/doc.html",
                        "/webjars/**",
                        "/ -resources/**",
                        "/v2/api-docs/**",
                        "/captcha/**",
                        "/wsyeb/**",
                        "/captcha/getCaptcha"
                );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
//                        .requestMatchers(
//                                "/login","/login/doLogin", "/login/doLogout", "/captcha/getCaptcha"," /system/cfg/menu"
//                        )
//                        .permitAll()
                        .anyRequest().authenticated()
        ).formLogin(formLogin ->
                formLogin.loginPage("/login").loginProcessingUrl("/doLogin")
                        .usernameParameter("username").passwordParameter("password")
                        .successHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            Object principal = authentication.getPrincipal();
                            resp.getWriter().write("success");
                        })
                        .failureHandler((req, resp, exception) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.getWriter().write("fail");
                        })
                        .permitAll()
        ).logout(logout ->
                logout.logoutUrl("/logout")
                        .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.getWriter().write("success");
                        })
        )
//        .objectPostProcessor(new ObjectPostProcessor<Object>() {
//            @Override
//            public <O> O postProcess(O object) {
//                ((AuthorizationFilter)object)
//                ((AuthorizationFilter)object).setAccessDecisionManager(customUrlDecisionManager)
//                return object;
//            }
//        })
        .csrf(
                AbstractHttpConfigurer::disable
        ).sessionManagement(sessionManagement ->
                // 基于token, 不需要session
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).cors(cors -> cors.configurationSource(configurationSource())
                ).httpBasic(AbstractHttpConfigurer::disable);

        // 添加jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 异常处理
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling
                        .accessDeniedHandler(restfulAccessDeniedHandler)
                        // .authenticationEntryPoint(restAuthorizationEntryPoint)
        );

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        ProviderManager pm = new ProviderManager(daoAuthenticationProvider);
        return pm;
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
