package com.rafaelleal.android.data_local.source

import com.rafaelleal.android.data_local.db.address.AddressDao
import com.rafaelleal.android.data_local.db.address.AddressEntity
import com.rafaelleal.android.data_repository.data_source.local.LocalAddressDataSource
import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAddressDataSourceImpl @Inject constructor(
    private val addressDao: AddressDao
) : LocalAddressDataSource {

    override fun getAddress(cep: String): Flow<Address>
    = addressDao.getAddress(cep).map{ _address ->
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

    override fun getAllAddresses(): Flow<List<Address>> =
        addressDao.getAllAddresses().map { _addresses ->
            _addresses.map{ _address ->
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
                id = address.id,
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
