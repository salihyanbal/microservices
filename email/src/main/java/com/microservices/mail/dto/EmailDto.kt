package com.microservices.mail.dto

data class EmailDto constructor(
        val id: String,
        val emailAddress: String,
        val confirmed: Boolean
)
