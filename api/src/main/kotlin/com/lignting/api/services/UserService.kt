package com.lignting.api.services

import com.lignting.api.repositories.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

interface IUserService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails
}

@Service
class UserService(
    private val userRepository: UserRepository
) : IUserService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            user.roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }
        )
    }
}