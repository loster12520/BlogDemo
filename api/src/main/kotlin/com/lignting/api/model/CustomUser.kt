package com.lignting.api.model

import jakarta.persistence.*
import org.springframework.beans.BeanUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


@Entity
data class CustomUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    @ManyToMany(fetch = FetchType.EAGER)
    val roles: Set<Role> = emptySet(),
) : User(username, password, true, true, true, true, roles)


data class ReaderUserDTO(
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var roles: Set<Role> = mutableSetOf(readerRole),
)

data class AuthorUserDTO(
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var roles: Set<Role> = mutableSetOf(readerRole, authorRole),
)

fun CustomUser.toReaderUserDTO(): ReaderUserDTO {
    val readerUserDTO = ReaderUserDTO()
    BeanUtils.copyProperties(this, readerUserDTO)
    return readerUserDTO
}

fun CustomUser.toAuthorUserDTO(): AuthorUserDTO {
    val authorUserDTO = AuthorUserDTO()
    BeanUtils.copyProperties(this, authorUserDTO)
    return authorUserDTO
}

fun ReaderUserDTO.toBase(): CustomUser {
    val user = CustomUser()
    BeanUtils.copyProperties(this, user)
    return user
}

fun AuthorUserDTO.toBase(): CustomUser {
    val user = CustomUser()
    BeanUtils.copyProperties(this, user)
    return user
}

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null
) : GrantedAuthority {
    override fun getAuthority() = name
}

val readerRole = Role(name = "Reader")
val authorRole = Role(name = "Author")