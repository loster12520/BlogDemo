package com.lignting.api.repositories

import com.lignting.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserByUsername(username: String): User?
}