package com.lignting.api.services

import com.lignting.api.model.Role
import com.lignting.api.model.User
import com.lignting.api.repositories.PermissionRepository
import com.lignting.api.repositories.RoleRepository
import com.lignting.api.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository
) {
    fun getUsers(): List<User> = userRepository.findAll()
    fun getUserById(id: Long): User = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
    fun getUSerByUsername(username: String) = userRepository.getUserByUsername(username)

    @PostConstruct
    fun init() {
        roleRepository.save(Role(name = "user"))
        roleRepository.save(Role(name = "admin"))
        userRepository.save(
            User(
                username = "admin_test",
                password = "admin123456",
                roles = mutableListOf<Role>().also { roles ->
                    roles.add(roleRepository.getRoleByName("admin") ?: throw RuntimeException("Admin not found"))
                }
            )
        )
        userRepository.save(
            User(
                username = "user_test",
                password = "user123456",
                roles = mutableListOf<Role>().also { roles ->
                    roles.add(roleRepository.getRoleByName("user") ?: throw RuntimeException("Admin not found"))
                }
            )
        )
    }
}