package com.lignting.api.controller

import com.lignting.api.models.UserDetailDTO
import com.lignting.api.services.IUserDetailService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserDetailController(private val userDetailService: IUserDetailService) {
    @GetMapping("/public/userDetail/{id}")
    fun getUserDetailsByUserId(@PathVariable id: Long): Any? =
        userDetailService.getUserDetailsByUserId(id)
    @PostMapping("/public/userDetail")
    fun add(@RequestBody userDetailDTO: UserDetailDTO): Any? =
        userDetailService.add(userDetailDTO)
}