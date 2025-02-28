package com.lignting.api.model

import jakarta.persistence.*


@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    val roles: Set<Role> = emptySet()
)

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String
)