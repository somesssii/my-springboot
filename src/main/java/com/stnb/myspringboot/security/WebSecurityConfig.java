//package com.stnb.myspringboot.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 描述：security配置类
// * @author 11299
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public CustomUserService customUserService() {
//        return new CustomUserService();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        /**
//         * 路由策略和访问权限的简单配置
//         */
//        http
//                .formLogin()  //启用默认登陆页面
//                .failureUrl("/login?error") //登录失败返回URL：/login?error
//                .defaultSuccessUrl("/ayUser/test") //登录成功跳转URL,跳转到首页
//                .permitAll(); //登录页面全部权限可访问
//        super.configure(http);
//    }
//
//    /**
//     * 配置内存用户
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
////                .userDetailsService(customUserService());
//                .inMemoryAuthentication()
//                .withUser("root").password("123456").roles("ADMIN")
//                .and()
//                .withUser("admin").password("123456").roles("USER");
//    }
//}
