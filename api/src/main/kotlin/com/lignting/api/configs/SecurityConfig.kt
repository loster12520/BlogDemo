package com.lignting.api.configs

import com.lignting.api.repositories.RoleRepository
import com.lignting.api.repositories.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.management.relation.Role

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) {
    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() } // 前后端分离建议禁用 CSRF
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    // 公开接口
                    .requestMatchers("/api/login", "/api/public/**").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginProcessingUrl("/api/login")
                    .successHandler(jsonLoginSuccessHandler())
                    .failureHandler(jsonLoginFailureHandler())
                    .permitAll()
            }
//            .logout { logout ->
//                logout
//                    .logoutUrl("/api/logout")
//                    .logoutSuccessHandler(jsonLogoutSuccessHandler())
//            }

        return http.build()
    }

    // 配置 CORS
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("http://localhost:5174", "http://localhost:5173") // Vue 前端地址
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        config.allowCredentials = true
        config.allowedHeaders = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    // JSON 登录成功处理器
    @Bean
    fun jsonLoginSuccessHandler(): AuthenticationSuccessHandler {
        return AuthenticationSuccessHandler { _, response, authentication ->
            response.contentType = "application/json"
            response.writer.write(
                """
                { "success": true, "user": "${authentication.name}" }
                """.trimIndent()
            )
        }
    }

    // JSON 登录失败处理器
    @Bean
    fun jsonLoginFailureHandler(): AuthenticationFailureHandler {
        return AuthenticationFailureHandler { request, response, e ->
            logger.error(e.message)
            response.status = 401
            response.contentType = "application/json"
            response.writer.write(
                """
                { "success": false, "error": "用户名或密码错误" }
                """.trimIndent()
            )
        }
    }

    @Bean
    fun initDefaultUser(): CommandLineRunner {
        return CommandLineRunner {
            // 1. 初始化角色（如果不存在）
            val adminRole =
                roleRepository.findByName("ADMIN") ?: roleRepository.save(com.lignting.api.model.Role(name = "ADMIN"))
            val userRole =
                roleRepository.findByName("USER") ?: roleRepository.save(com.lignting.api.model.Role(name = "USER"))

            // 2. 初始化默认用户（如果不存在）
            if (!userRepository.existsByUsername("admin")) {
                userRepository.save(
                    com.lignting.api.model.User(
                        username = "admin",
                        password = passwordEncoder().encode("admin123"), // 必须加密
                        roles = setOf(adminRole)
                    )
                )
            }

            if (!userRepository.existsByUsername("user")) {
                userRepository.save(
                    com.lignting.api.model.User(
                        username = "user",
                        password = passwordEncoder().encode("user123"),
                        roles = setOf(userRole)
                    )
                )
            }
        }
    }
}