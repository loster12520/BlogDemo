package com.lignting.api.repositories

import com.lignting.api.model.Permission
import com.lignting.api.model.Role
import com.lignting.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserByUsername(username: String): User?
}

@Repository
interface PermissionRepository : JpaRepository<Permission, Long>{
    fun getPermissionByName(name: String): Permission?
}

@Repository
interface RoleRepository : JpaRepository<Role, Long>{
    fun getRoleByName(name: String): Role?
}