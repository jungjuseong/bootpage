package com.clbee.pbcms.config;

import com.clbee.pbcms.security.AuthFailureHandler;
import com.clbee.pbcms.security.AuthSuccessHandler;
import com.clbee.pbcms.security.LoginSuccessHandler;
import com.clbee.pbcms.security.MySpecialAuthenticationSuccessHandler;
import com.clbee.pbcms.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    private AuthFailureHandler authFailureHandler;
    private AuthSuccessHandler authSuccessHandler;
    private MySpecialAuthenticationSuccessHandler mySpecialAuthenticationSuccessHandler;
//
//    @Bean
//    SessionRegistryImpl sessionRegistry() {
//    	    return new SessionRegistryImpl();
//    }
//
    @Bean
    RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        Map<String, List<String>> roleMap = new HashMap<>();
        roleMap.put("ROLE_COMPANY_MIDDLEADMIN", Arrays.asList("ROLE_COMPANY_MEMBER"));
        roleMap.put("ROLE_COMPANY_MEMBER", Arrays.asList("ROLE_COMPANY_DISTRIBUTOR"));
        roleMap.put("ROLE_COMPANY_DISTRIBUTOR", Arrays.asList("ROLE_COMPANY_CREATOR"));
        roleMap.put("ROLE_COMPANY_CREATOR", Arrays.asList("ROLE_COMPANY_USER"));
        roleMap.put("ROLE_INDIVIDUAL_MEMBER", Arrays.asList("ROLE_COMPANY_DISTRIBUTOR"));
        roleMap.put("ROLE_ADMIN_SERVICE", Arrays.asList("ROLE_COMPANY_CREATOR"));

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
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**",  "/images/**", "/font/**", "/resources/**", "/_upload/_temp/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/hello", "/main",
                        "/favicon.ico",
                        "/error**",
                        "/index.html",
                        "/findid.html",
                        "/send_id_mail.html",
                        "/findpwd.html",
                        "/loginVerify.html",
                        "/inAppJsonSerializer.html",
                        "/printAnswer.html",
                        "/viewJsonAnswer.html").permitAll()
                .antMatchers(HttpMethod.POST, "/member/userStatusValid", "/member/userIdValidation", "/member/userIdValidation").permitAll()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/contents/**").hasAnyRole("ROLE_COMPANY_MIDDLEADMIN", "ROLE_COMPANY_CREATOR", "ROLE_ADMIN_SERVICE")
                .antMatchers("/app/**").hasAnyRole("ROLE_COMPANY_MIDDLEADMIN", "ROLE_COMPANY_CREATOR", "ROLE_ADMIN_SERVICE")
                .antMatchers("/templates/**").access("ROLE_ADMIN_SERVICE")
                .antMatchers("/man/**").hasAnyRole("ROLE_COMPANY_MIDDLEADMIN", "ROLE_ADMIN_SERVICE", "ROLE_INDIVIDUAL_MEMBER", "ROLE_COMPANY_MEMBER", "ROLE_COMPANY_DISTRIBUTOR")
                .antMatchers("/book/**").hasAnyRole("ROLE_COMPANY_MIDDLEADMIN", "ROLE_COMPANY_CREATOR", "ROLE_ADMIN_SERVICE")
                .antMatchers("/admin/**").access("ROLE_ADMIN_SERVICE")
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated() // 모든 요청에 대해, 인증된 사용자만 접근
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/index.html")
                .successHandler(authSuccessHandler)
                .defaultSuccessUrl("/")
                .failureUrl("/error.html")
                .failureHandler(authFailureHandler)
                .successHandler(mySpecialAuthenticationSuccessHandler)
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/index.html")
                .invalidateHttpSession(true)
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

//    @Override
//    protected UserDetailsService userDetailsService() { return userDetailsService; }

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