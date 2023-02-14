package com.rafaelleal.android.data_remote.source

import android.util.Log
import com.rafaelleal.android.data_remote.networking.address.AddressApiModel
import com.rafaelleal.android.data_remote.networking.address.AddressService
import com.rafaelleal.android.data_repository.data_source.remote.RemoteAddressDataSource
import com.rafaelleal.android.domain.entity.Address
import com.rafaelleal.android.domain.sealed.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteAddressDataSourceImpl @Inject constructor(
    private val addressService: AddressService
) : RemoteAddressDataSource {

    val TAG = "Main"
    override fun getAddress(cep: String): Flow<Address> = flow {
        emit(addressService.getAddress(cep))
    }.map { _address ->
        convert(_address)
    }.catch {
        throw UseCaseException.AddressException(it)
    }

    fun convert(addressApiModel: AddressApiModel) = Address(
        id = 0L,
        cep = addressApiModel.cep ?: "Não encontrado" ,
        rua = addressApiModel.logradouro ?: "Não encontrado",
        complemento = addressApiModel.complemento ?: "Não encontrado",
        bairro = addressApiModel.bairro ?: "Não encontrado",
        cidade = addressApiModel.localidade ?: "Não encontrado",
        estado = addressApiModel.uf ?: "Não encontrado"
    )
}


