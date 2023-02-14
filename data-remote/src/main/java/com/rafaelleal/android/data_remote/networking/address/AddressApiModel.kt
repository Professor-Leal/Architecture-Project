package com.rafaelleal.android.data_remote.networking.address

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressApiModel(
    val cep: String? = "",
    val logradouro: String? = "",
    val complemento: String? = "",
    val bairro: String? = "",
    val localidade: String? = "",
    val uf: String? = "",
    val ibge: String? = "",
    val gia: String? = "",
    val ddd: String? = "",
    val siafi: String? = ""
)