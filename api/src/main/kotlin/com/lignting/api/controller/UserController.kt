package com.lignting.api.controller

import com.lignting.api.model.ResponseMessage
import com.lignting.api.model.success
import com.lignting.api.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/user")
    fun getUser(@RequestParam(value = "username", required = false) username: String): ResponseMessage<UserDetails> =
        userService.loadUserByUsername(username).success()
}