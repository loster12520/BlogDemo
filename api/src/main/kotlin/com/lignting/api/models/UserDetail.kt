package com.lignting.api.models

import jakarta.persistence.*
import org.springframework.beans.BeanUtils

@Entity
data class UserDetail(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L,
    var avatar: String = "",
    var name: String = "",
    var email: String = "",

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(nullable = false)
    var user: CustomUserModel? = null
)

data class UserDetailDTO(
    var avatar: String = "",
    var name: String = "",
    var email: String = "",
    var userId: Long = 0L,
)

fun UserDetail.toDTO(): UserDetailDTO {
    val result = UserDetailDTO()
    BeanUtils.copyProperties(this, result)
    result.userId = this.user?.id ?: 0L
    return result
}

fun UserDetailDTO.toBase(): UserDetail {
    val result = UserDetail()
    BeanUtils.copyProperties(this, result)
    return result
}