package com.rafaelleal.android.domain.repository

import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddressByCep(cep: String) : Flow<Address>
    fun getAllAddresses() : Flow<List<Address>>
    suspend fun saveAddress(address: Address)
}

