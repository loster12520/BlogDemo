package com.lignting.api.controller

import com.lignting.api.model.ResponseMessage
import com.lignting.api.model.failed
import com.lignting.api.model.success
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/public/user")
    fun login(@RequestParam username: String, @RequestParam password: String): ResponseMessage<Boolean> {
        val subject = SecurityUtils.getSubject()
        try {
            subject.login(UsernamePasswordToken(username, password))
            return subject.isAuthenticated.success()
        } catch (e: IncorrectCredentialsException) {
            return subject.isAuthenticated.failed()
        }
    }
}