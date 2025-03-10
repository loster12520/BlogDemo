package com.lignting.api.model

import jakarta.persistence.*


@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var username: String = "",
    var password: String = "",
    @OneToMany(mappedBy = "user", cascade = [CascadeType.MERGE], orphanRemoval = true)
    var permissions: MutableList<Permission> = mutableListOf(),
    @OneToMany(mappedBy = "user", cascade = [CascadeType.MERGE], orphanRemoval = true)
    var roles: MutableList<Role> = mutableListOf()
)

@Entity
data class Permission(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var name: String = ""
)

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var name: String = ""
)