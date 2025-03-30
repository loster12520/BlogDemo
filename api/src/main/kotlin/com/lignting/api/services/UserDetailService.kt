package com.lignting.api.services

import com.lignting.api.models.UserDetail
import com.lignting.api.models.UserDetailDTO
import com.lignting.api.models.toDTO
import com.lignting.api.repositories.UserDetailRepository
import com.lignting.api.repositories.UserRepository
import org.springframework.stereotype.Service

interface IUserDetailService {
    fun getUserDetailsByUserId(id: Long): Any?
    fun add(userDetailDTO: UserDetailDTO): Any?
}

@Service
class UserDetailService(
    private val userDetailRepository: UserDetailRepository,
    private val userRepository: UserRepository
) : IUserDetailService {
    override fun getUserDetailsByUserId(id: Long): UserDetailDTO =
        userDetailRepository.findByUserId(id).orElseThrow {
            RuntimeException("UserDetail not found")
        }.toDTO()

    override fun add(userDetailDTO: UserDetailDTO) =
        userDetailRepository.save(userDetailDTO.let {
            UserDetail(
                avatar = it.avatar,
                name = it.name,
                email = it.email,
                user = userRepository.findById(it.userId).orElseThrow { RuntimeException("User not found") },
            )
        }).toDTO()
}