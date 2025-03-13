package com.lignting.api.controller

import com.lignting.api.model.*
import com.lignting.api.repositories.UserRepository
import com.lignting.api.services.UserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/user")
class UserController(private val userService: UserService) {
    @GetMapping("/login")
    fun login(@RequestBody user: UserDTO): ResponseMessage<Boolean> {
        val subject = SecurityUtils.getSubject()
        try {
            subject.login(UsernamePasswordToken(user.username, user.password))
            return subject.isAuthenticated.success()
        } catch (e: AuthenticationException) {
            return subject.isAuthenticated.failed(e.message!!)
        }
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody user: UserDTO) =
        userService.saveUser(
            user
        ).success()
}