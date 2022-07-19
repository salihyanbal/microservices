package com.microservices.email.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Email @JvmOverloads constructor(
        val id: String,
        val emailAddress: String,
        val code: Int,
        val confirmed: Boolean
)
