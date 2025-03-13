package com.lignting.api.model

import jakarta.persistence.*
import org.springframework.beans.BeanUtils


@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var username: String = "",
    var password: String = "",
    @ManyToMany(mappedBy = "users", cascade = [CascadeType.MERGE])
    var permissions: MutableList<Permission> = mutableListOf(),
    @ManyToMany(mappedBy = "users", cascade = [CascadeType.MERGE])
    var roles: MutableList<Role> = mutableListOf()
)

data class UserDTO(
    var username: String = "",
    var password: String = "",
)

fun User.toDTO(): UserDTO {
    val res = UserDTO()
    BeanUtils.copyProperties(this, res)
    return res
}

fun UserDTO.toBase(): User {
    val res = User()
    BeanUtils.copyProperties(this, res)
    return res
}

@Entity
data class Permission(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var name: String = "",
    @ManyToMany
    var users: MutableList<User> = mutableListOf(),
)

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var name: String = "",
    @ManyToMany
    var users: MutableList<User> = mutableListOf(),
)