package com.lignting.api.controller

import com.lignting.api.model.ResponseMessage
import com.lignting.api.model.User
import com.lignting.api.model.failed
import com.lignting.api.model.success
import com.lignting.api.repositories.UserRepository
import com.lignting.api.services.UserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/user")
class UserController(private val userService: UserService) {
    @GetMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): ResponseMessage<Boolean> {
        val subject = SecurityUtils.getSubject()
        try {
            subject.login(UsernamePasswordToken(username, password))
            return subject.isAuthenticated.success()
        } catch (e: AuthenticationException) {
            return subject.isAuthenticated.failed(e.message!!)
        }
    }

    @PostMapping("/signIn")
    fun signIn(@RequestParam username: String, @RequestParam password: String) =
        userService.saveUser(
            User(username = username, password = password)
        ).success()
}