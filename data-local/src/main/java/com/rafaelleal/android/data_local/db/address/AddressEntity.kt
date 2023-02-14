package com.rafaelleal.android.data_local.db.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "cep") val cep: String,
    @ColumnInfo(name = "rua") val rua: String,
    @ColumnInfo(name = "complemento") val complemento: String,
    @ColumnInfo(name = "bairro") val bairro: String,
    @ColumnInfo(name = "cidade") val cidade: String,
    @ColumnInfo(name = "estado") val estado: String
)
