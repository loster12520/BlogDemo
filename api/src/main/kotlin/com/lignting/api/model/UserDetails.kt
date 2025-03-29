package com.lignting.api.model

import jakarta.persistence.*
import org.springframework.beans.BeanUtils

@Entity
data class UserDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var avatar: String = "https://example.com/avatar.png",
    var email: String = "",

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(nullable = false)
    var user: User? = null
)

data class UserDetailsDTO(
    var avatar: String = "",
    var email: String = "",
    var user: User? = null
)

fun UserDetails.toDTO(): UserDetailsDTO {
    val result = UserDetailsDTO()
    BeanUtils.copyProperties(this, result)
    return result
}

fun UserDetailsDTO.toBase(): UserDetails {
    val result = UserDetails()
    BeanUtils.copyProperties(this, result)
    return result
}