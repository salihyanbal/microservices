package com.microservices.email.dto

data class UpdateEmailRequest constructor(
        val emailAddress: String,
        val code: Int,
        val confirmed: Boolean
)