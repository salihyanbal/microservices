package com.microservices.user.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User @JvmOverloads constructor(
        val id: String? = null,
        val userName: String? = null,
        val password: String? = null,
        val email: String? = null,
        val firstName: String? = null,
        val lastName: String? = null
)
