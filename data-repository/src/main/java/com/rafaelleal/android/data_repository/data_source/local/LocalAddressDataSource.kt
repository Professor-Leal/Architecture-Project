package com.rafaelleal.android.data_repository.data_source.local

import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.Flow


interface LocalAddressDataSource {

    fun getAddress(cep: String) : Flow<Address>
    fun getAllAddresses() : Flow<List<Address>>
    suspend fun saveAddress(address: Address)

}


