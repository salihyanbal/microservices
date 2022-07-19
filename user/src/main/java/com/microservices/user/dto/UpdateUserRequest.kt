package com.microservices.user.dto

import javax.validation.constraints.NotBlank

data class UpdateUserRequest constructor(
        val userName: String? = null,
        val email: String? = null,
        val firstName: String? = null,
        val lastName: String? = null
)
