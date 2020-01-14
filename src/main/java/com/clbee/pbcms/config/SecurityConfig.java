package com.clbee.pbcms.config;

import com.clbee.pbcms.security.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MyUserDetailsService myUserDetailsService;
    private MySpecialAuthenticationSuccessHandler mySpecialAuthenticationSuccessHandler;

    @Bean
    RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        //ROLE_ADMIN_SERVICE -> ROLE_INDIVIDUAL_MEMBER / ROLE_COMPANY_MEMBER -> ROLE_USER
        Map<String, List<String>> roleMap = new HashMap<>();
        roleMap.put("ROLE_COMPANY_MEMBER", Arrays.asList("ROLE_USER"));
        roleMap.put("ROLE_INDIVIDUAL_MEMBER", Arrays.asList("ROLE_COMPANY_MEMBER"));
        roleMap.put("ROLE_ADMIN_SERVICE", Arrays.asList("ROLE_INDIVIDUAL_MEMBER"));

        hierarchy.setHierarchy(RoleHierarchyUtils.roleHierarchyFromMap(roleMap));
        return hierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }

    @Bean
    public RoleHierarchyVoter roleHierarchyVoter() {
        return new  RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        WebExpressionVoter webVoter = new WebExpressionVoter();
        webVoter.setExpressionHandler(webExpressionHandler());
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(roleHierarchyVoter(), webVoter, new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Override
    public void configure(WebSecurity web) throws Exception  {
        web.ignoring().antMatchers("/css/**", "/js/**",  "/images/**", "/font/**", "/resources/**", "/_upload/_temp/images/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/index.html",
                        "/favicon.ico",
                        "/error**",
                        "/denied",
                        "/send_id_mail.html",
                        "/inAppJsonSerializer.html",
                        "/printAnswer.html",
                        "/viewJsonAnswer.html").permitAll()
                .antMatchers(HttpMethod.POST,"/member/**").permitAll()
                .antMatchers("/contents/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/app/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/templates/**").access("ROLE_ADMIN_SERVICE")
                .antMatchers("/man/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/distribute/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/mypage/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/my/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers("/book/**").hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated() // 모든 요청에 대해, 인증된 사용자만 접근
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/loginProcess")
                .successHandler(mySpecialAuthenticationSuccessHandler)
                .failureUrl("/index.html")
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index.html")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/denied");
    }

    @Override
    protected UserDetailsService userDetailsService() { return myUserDetailsService; }

    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler("/index.html");
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}