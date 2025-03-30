package com.lignting.api.repositories

import com.lignting.api.models.UserDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserDetailRepository : JpaRepository<UserDetail, Long> {
    fun findByUserId(userId: Long): Optional<UserDetail>
}