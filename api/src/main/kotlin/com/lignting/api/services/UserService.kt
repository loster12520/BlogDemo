package com.lignting.api.services

import com.lignting.api.models.CustomUserModel
import com.lignting.api.repositories.UserRepository
import org.springframework.stereotype.Service

interface IUserService {
    fun getUserById(id: Long): Any?
    fun getAllUsers(): Any?
}

@Service
class UserService(private val userRepository: UserRepository) : IUserService {
    override fun getUserById(id: Long): CustomUserModel =
        userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found") }

    override fun getAllUsers(): List<CustomUserModel>? =
        userRepository.findAll()
            .ifEmpty { null }
}