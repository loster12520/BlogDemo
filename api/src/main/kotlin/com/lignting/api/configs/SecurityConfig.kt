package com.lignting.api.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun userDetailsService(): UserDetailsService =
        InMemoryUserDetailsManager(
            User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build(),
            User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build()
        )

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() } // 禁用CSRF保护
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 配置路径权限
                    .requestMatchers("/user/**").hasRole("USER")
                    .requestMatchers("/", "/home", "/register").permitAll() // 允许匿名访问的路径
                    .anyRequest().authenticated() // 其他请求需要认证
            }
            .formLogin { login ->
                login
                    .loginPage("/login") // 自定义登录页面
                    .permitAll() // 允许匿名访问登录页面
            }
            .logout { logout ->
                logout
                    .logoutRequestMatcher(AntPathRequestMatcher("/logout")) // 登出路由
                    .permitAll() // 允许匿名访问登出页面
            }
            .build()
}