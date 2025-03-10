package com.lignting.api.services

import com.lignting.api.model.User
import com.lignting.api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<User> = userRepository.findAll()
    fun getUserById(id: Long): User = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
    fun getUSerByUsername(username: String) = userRepository.getUserByUsername(username)
}