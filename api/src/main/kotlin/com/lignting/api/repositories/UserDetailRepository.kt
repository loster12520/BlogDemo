package com.lignting.api.repositories

import com.lignting.api.model.User
import com.lignting.api.model.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserDetailRepository : JpaRepository<UserDetails, Long> {
    fun findByUser(user: User): Optional<UserDetails>
}