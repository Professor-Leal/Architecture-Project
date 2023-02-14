package com.rafaelleal.android.data_local.source

import android.util.Log
import com.rafaelleal.android.data_local.db.address.AddressDao
import com.rafaelleal.android.data_local.db.address.AddressEntity
import com.rafaelleal.android.data_repository.data_source.local.LocalAddressDataSource
import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalAddressDataSourceImpl @Inject constructor(
    private val addressDao: AddressDao
) : LocalAddressDataSource {

    override fun getAddress(cep: String): Flow<Address> = flow {
        val _address = addressDao.getAddress(cep).firstOrNull()
        if (_address == null) {
            emit(
                Address(
                    id = 0L,
                    cep = "",
                    rua = "",
                    complemento = "",
                    bairro = "",
                    cidade = "",
                    estado = ""
                )
            )
        } else {
            emit(
                Address(
                    id = _address.id,
                    cep = _address.cep,
                    rua = _address.rua,
                    complemento = _address.complemento,
                    bairro = _address.bairro,
                    cidade = _address.cidade,
                    estado = _address.estado
                )
            )
        }
    }

    override fun getAllAddresses(): Flow<List<Address>> =
        addressDao.getAllAddresses().map { _addresses ->
            _addresses.map { _address ->
                Address(
                    id = _address.id,
                    cep = _address.cep,
                    rua = _address.rua,
                    complemento = _address.complemento,
                    bairro = _address.bairro,
                    cidade = _address.cidade,
                    estado = _address.estado
                )
            }

        }

    override suspend fun saveAddress(address: Address) {
        addressDao.saveAddress(
            AddressEntity(
                id = 0L,
                cep = address.cep,
                rua = address.rua,
                complemento = address.complemento,
                bairro = address.bairro,
                cidade = address.cidade,
                estado = address.estado
            )
        )
    }

}
