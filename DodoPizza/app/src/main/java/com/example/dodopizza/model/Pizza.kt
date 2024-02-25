package com.example.dodopizza.model

import java.util.UUID

data class Pizza(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val description: String,
    val size: String,
    val imageRes: Int
)