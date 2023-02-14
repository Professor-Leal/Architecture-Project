package com.rafaelleal.android.data_repository.repository

import android.util.Log
import com.rafaelleal.android.data_repository.data_source.local.LocalAddressDataSource
import com.rafaelleal.android.data_repository.data_source.remote.RemoteAddressDataSource
import com.rafaelleal.android.domain.entity.Address
import com.rafaelleal.android.domain.repository.AddressRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val remoteAddressDataSource: RemoteAddressDataSource,
    private val localAddressDataSource: LocalAddressDataSource
) : AddressRepository {

    override fun getAddressByCep(cep: String): Flow<Address> = flow{

        val address = localAddressDataSource.getAddress(formatCep(cep)).single()
        if(address.id == 0L ){
            emit(
                remoteAddressDataSource.getAddress(cep).onEach { _address->
                    localAddressDataSource.saveAddress(_address)
                }.single()
            )
        } else {
            emit(address)
        }
    }
    override fun getAllAddresses(): Flow<List<Address>> {
        return localAddressDataSource.getAllAddresses()
    }
    override suspend fun saveAddress(address: Address) {
        localAddressDataSource.saveAddress(address)
    }

    fun formatCep(cep: String ): String {
        return "${cep.substring(0,5)}-${cep.substring(5,8)}"
    }
}

