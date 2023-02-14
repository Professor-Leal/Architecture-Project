package com.rafaelleal.android.domain.entity

data class Address(
    val id: Long,
    val cep: String,
    val rua: String,
    val complemento: String,
    val bairro: String,
    val cidade: String,
    val estado: String
)
