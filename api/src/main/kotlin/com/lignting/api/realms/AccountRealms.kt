package com.lignting.api.realms

import com.lignting.api.services.UserService
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.stereotype.Component


@Component
class AccountRealms(private val userService: UserService) : AuthorizingRealm() {
    override fun doGetAuthenticationInfo(authenticationToken: AuthenticationToken?): AuthenticationInfo {
        val token = authenticationToken as UsernamePasswordToken
        val user = userService.getUSerByUsername(token.username) ?: throw RuntimeException()
        return SimpleAuthenticationInfo(user, user.password, name)
    }

    override fun doGetAuthorizationInfo(p0: PrincipalCollection?): AuthorizationInfo {
        TODO("Not yet implemented")
    }
}