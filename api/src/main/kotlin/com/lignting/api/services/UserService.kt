package com.lignting.api.services

import com.lignting.api.model.Role
import com.lignting.api.model.User
import com.lignting.api.model.UserDetailsDTO
import com.lignting.api.model.toBase
import com.lignting.api.repositories.PermissionRepository
import com.lignting.api.repositories.RoleRepository
import com.lignting.api.repositories.UserDetailRepository
import com.lignting.api.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.hibernate.Hibernate
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository,
    private val userDetailRepository: UserDetailRepository
) {
    fun getUserByUsername(username: String) = userRepository.getUserByUsername(username).get().also {
        Hibernate.initialize(it.roles)
        Hibernate.initialize(it.permissions)
    }

    fun saveUser(user: User) = userRepository.save(user)

    /**
     * test
     */
    @PostConstruct
    fun init() {
        val adminRole = roleRepository.getRoleByName("admin").orElseGet {
            roleRepository.save(Role(name = "admin"))
        }
        val userRole = roleRepository.getRoleByName("user").orElseGet {
            roleRepository.save(Role(name = "user"))
        }
        userRepository.getUserByUsername("admin_test").orElseGet {
            userRepository.save(
                User(
                    username = "admin_test",
                    password = "admin123456",
                    roles = mutableListOf(adminRole)
                )
            )
        }
        userRepository.getUserByUsername("user_test").orElseGet {
            userRepository.save(
                User(
                    username = "user_test",
                    password = "user123456",
                    roles = mutableListOf(userRole)
                )
            )
        }
        userRepository.getUserByUsername("lignting").orElseGet {
            userRepository.save(
                User(
                    username = "lignting",
                    password = "lignting",
                    roles = mutableListOf(adminRole, userRole)
                )
            )
        }.also { user ->
            userDetailRepository.findByUser(user).orElseGet {
                userDetailRepository.save(
                    UserDetailsDTO(
                        avatar = "https://i0.hdslb.com/bfs/face/a8e277f277a7acf81c67705652368205d20a10b6.jpg",
                        email = "xiaoshuo12520@outlook.com",
                        user = user
                    ).toBase()
                )
            }
        }
    }
}