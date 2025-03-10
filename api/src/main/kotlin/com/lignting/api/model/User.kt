package com.lignting.api.model

import jakarta.persistence.*


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