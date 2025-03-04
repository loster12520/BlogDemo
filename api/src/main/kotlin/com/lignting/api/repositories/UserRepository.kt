package com.lignting.api.repositories

import com.lignting.api.model.Role
import com.lignting.api.model.CustomUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<CustomUser, Long> {
    fun findByUsername(username: String): CustomUser?
}

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}