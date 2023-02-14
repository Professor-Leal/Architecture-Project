package com.rafaelleal.android.data_repository.data_source.remote

import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.Flow

interface RemoteAddressDataSource {
    fun getAddress( cep: String ) : Flow<Address>
}

