package com.lignting.api.services

import com.lignting.api.model.Role
import com.lignting.api.model.User
import com.lignting.api.model.UserDTO
import com.lignting.api.model.toBase
import com.lignting.api.repositories.PermissionRepository
import com.lignting.api.repositories.RoleRepository
import com.lignting.api.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.hibernate.Hibernate
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository
) {
    fun getUserByUsername(username: String) = userRepository.getUserByUsername(username).get().also {
        Hibernate.initialize(it.roles)
        Hibernate.initialize(it.permissions)
    }
    fun saveUser(user: UserDTO) = userRepository.save(user.toBase())

    /**
     * test
     */
    @PostConstruct
    fun init() {
        val adminRole = roleRepository.getRoleByName("admin").orElseGet {
            roleRepository.save(Role(name = "admin"))
        }
        val userRole = roleRepository.getRoleByName("user").orElseGet {
            roleRepository.save(Role(name = "user"))
        }
        userRepository.getUserByUsername("admin_test").orElseGet {
            userRepository.save(
                User(
                    username = "admin_test",
                    password = "admin123456",
                    roles = mutableListOf(adminRole)
                )
            )
        }
        userRepository.getUserByUsername("user_test").orElseGet {
            userRepository.save(
                User(
                    username = "user_test",
                    password = "user123456",
                    roles = mutableListOf(userRole)
                )
            )
        }
    }
}