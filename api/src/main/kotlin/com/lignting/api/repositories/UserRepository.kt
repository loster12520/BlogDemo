package com.lignting.api.repositories

import com.lignting.api.model.Role
import com.lignting.api.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}