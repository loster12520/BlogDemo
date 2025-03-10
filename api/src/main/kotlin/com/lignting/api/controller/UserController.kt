package com.lignting.api.controller

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/user")
    fun login(username: String, password: String): Boolean {
        val subject = SecurityUtils.getSubject()
        subject.login(UsernamePasswordToken(username, password))
        return subject.isAuthenticated
    }
}