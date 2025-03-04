package com.lignting.api.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityContext(): SecurityContext = SecurityContextHolder.getContext()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests {
                it.requestMatchers("/login").permitAll()
                    .requestMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/profile/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .cors {
                it.configurationSource(corsConfigurationSource())
            }
            .build()

    private fun corsConfigurationSource(): CorsConfigurationSource =
        CorsConfiguration().also {
            it.allowedHeaders = Collections.singletonList("*")
            it.allowedMethods = Collections.singletonList("*")
            it.allowedOrigins = Collections.singletonList("*")
            it.setMaxAge(3600L)
        }.let { configuration ->
            UrlBasedCorsConfigurationSource().also {
                it.registerCorsConfiguration("/**", configuration)
            }
        }

}