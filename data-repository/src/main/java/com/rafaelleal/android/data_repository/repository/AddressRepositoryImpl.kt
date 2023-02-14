package com.rafaelleal.android.data_repository.repository

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
        val address = localAddressDataSource.getAddress(cep).single()
        if(address == null){
            emit(
                remoteAddressDataSource.getAddress(cep).onEach {
                    localAddressDataSource.saveAddress(it)
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
}

