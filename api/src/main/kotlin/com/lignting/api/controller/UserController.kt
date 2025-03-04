package com.lignting.api.controller

import com.lignting.api.model.ReaderUserDTO
import com.lignting.api.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @PostMapping("/login/reader")
    fun readerLogin(@RequestBody readerUserDTO: ReaderUserDTO){

    }
}