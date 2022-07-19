package com.microservices.mail.dto

data class UpdateEmailRequest constructor(
        val emailAddress: String,
        val code: Int,
        val confirmed: Boolean
)