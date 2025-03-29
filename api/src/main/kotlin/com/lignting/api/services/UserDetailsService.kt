package com.lignting.api.services

import com.lignting.api.model.User
import com.lignting.api.model.UserDetails
import com.lignting.api.model.UserDetailsDTO
import com.lignting.api.model.toBase
import com.lignting.api.repositories.UserDetailRepository
import com.lignting.api.repositories.UserRepository
import org.springframework.stereotype.Service

interface IUserDetailsService {
    fun create(dto: UserDetailsDTO): UserDetails
    fun getByUserId(id: Long): UserDetails
    fun getByUserName(name: String): UserDetails
    fun getByUser(user: User): UserDetails
    fun update(id: Long, dto: UserDetailsDTO): UserDetails
    fun delete(id: Long)
}

@Service
class UserDetailsService(
    private val userDetailRepository: UserDetailRepository,
    private val userRepository: UserRepository
) : IUserDetailsService {
    override fun create(dto: UserDetailsDTO) = userDetailRepository.save(dto.toBase())

    override fun getByUserId(id: Long): UserDetails = getByUser(
        userRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("User with id $id not found")
        }
    )

    override fun getByUserName(name: String): UserDetails = getByUser(
        userRepository.getUserByUsername(name).orElseThrow {
            throw IllegalArgumentException("User with name $name not found")
        }
    )

    override fun getByUser(user: User): UserDetails = userDetailRepository.findByUser(
        user
    ).orElseThrow {
        throw IllegalArgumentException("User details with id ${user.id} not found")
    }

    override fun update(id: Long, dto: UserDetailsDTO) = userDetailRepository.save(dto.toBase())

    override fun delete(id: Long) = userDetailRepository.delete(getByUserId(id))
}