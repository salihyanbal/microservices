package com.microservices.email.dto

data class EmailDto constructor(
        val id: String,
        val emailAddress: String,
        val confirmed: Boolean
)
