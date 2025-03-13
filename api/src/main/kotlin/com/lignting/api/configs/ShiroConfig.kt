package com.lignting.api.configs

import com.lignting.api.realms.AccountRealms
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ShiroConfig {
    @Bean
    fun getDefaultWebSecurityManager(accountRealms: AccountRealms) = DefaultWebSecurityManager(accountRealms)

    @Bean
    fun getShiroFilterFactoryBean(defaultWebSecurityManager: DefaultWebSecurityManager) =
        ShiroFilterFactoryBean().also {
            it.securityManager = defaultWebSecurityManager
            it.filterChainDefinitionMap.putAll(
                mapOf(
                    "/**" to "anon",
//                    "/public/**" to "anon",
//                    "/**" to "authc"
                )
            )
        }
}