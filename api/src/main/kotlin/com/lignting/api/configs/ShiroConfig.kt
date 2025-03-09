package com.lignting.api.configs

import org.apache.shiro.realm.text.IniRealm
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ShiroConfig {
    @Bean
    fun getIniRealm() = IniRealm()

    @Bean
    fun getDefaultWebSecurityManager(iniRealm: IniRealm) = DefaultWebSecurityManager(iniRealm)

    @Bean
    fun getShiroFilterFactoryBean(defaultWebSecurityManager: DefaultWebSecurityManager) =
        ShiroFilterFactoryBean().also {
            it.securityManager = defaultWebSecurityManager
            it.filterChainDefinitionMap.putAll(
                mapOf(

                )
            )
        }
}