package com.rafaelleal.android.presentation_address.address

data class AddressModel(
    val id: Long,
    val cep: String,
    val rua: String,
    val complemento: String,
    val bairro: String,
    val cidade: String,
    val estado: String
)