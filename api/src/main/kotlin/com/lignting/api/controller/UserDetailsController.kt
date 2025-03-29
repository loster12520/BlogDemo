package com.lignting.api.controller

import com.lignting.api.model.UserDetailsDTO
import com.lignting.api.services.UserDetailsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public/user-details")
class UserDetailsController(private val service: UserDetailsService) {

    @PostMapping("/create")
    fun create(@RequestBody dto: UserDetailsDTO) = service.create(dto)

    @GetMapping("/{name}")
    fun getByUserName(@PathVariable name: String) = service.getByUserName(name)

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: UserDetailsDTO) = service.update(id, dto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}