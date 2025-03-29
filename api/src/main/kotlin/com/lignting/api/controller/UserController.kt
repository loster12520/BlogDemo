package com.lignting.api.controller

import com.lignting.api.model.*
import com.lignting.api.repositories.UserRepository
import com.lignting.api.services.UserService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public/user")
class UserController(private val userService: UserService) {
    @PostMapping("/login")
    fun login(@RequestBody userDTO: UserDTO): ResponseMessage<Boolean> {
        val username = userDTO.username
        val password = userDTO.password
        val subject = SecurityUtils.getSubject()
        try {
            subject.login(UsernamePasswordToken(username, password))
            return subject.isAuthenticated.success()
        } catch (e: AuthenticationException) {
            return subject.isAuthenticated.failed(e.message!!)
        }
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody userDTO: UserDTO) =
        userService.saveUser(
            userDTO.toBase()
        ).success()
}