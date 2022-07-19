package com.microservices.user.dto

data class UserDto @JvmOverloads constructor(
        val id: String? = null,
        val userName: String? = null,
        val email: String? = null,
        val firstName: String? = null,
        val lastName: String? = null
)
