package com.microservices.user.dto
import javax.validation.constraints.NotBlank

data class CreateUserRequest constructor(
        @field:NotBlank
        val userName: String?,
        @field:NotBlank
        val password: String?,
        @field:NotBlank
        val email: String?,
        val firstName: String? = null,
        val lastName: String? = null
)
