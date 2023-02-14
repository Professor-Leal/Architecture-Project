package com.rafaelleal.android.presentation_address.address

import android.content.Context
import com.rafaelleal.android.domain.usecase.GetAddressUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.rafaelleal.android.domain.sealed.Result
import com.rafaelleal.android.presentation_address.R

class AddressConverter @Inject constructor(@ApplicationContext private val context : Context){

    fun convert(
        addressResult: Result<GetAddressUseCase.Response>
    ) : UiState<AddressModel> {
        return when (addressResult){
            is Result.Error -> {
                UiState.Error( addressResult.exception.localizedMessage.orEmpty() )
            }

            is Result.Success -> {
                UiState.Success(
                    AddressModel(
                        id = addressResult.data.address.id,
                        cep = context.getString(
                            R.string.cep_address, addressResult.data.address.cep),
                        rua = context.getString(
                            R.string.rua_address, addressResult.data.address.rua),
                        complemento = context.getString(
                            R.string.complemento_address, addressResult.data.address.complemento),
                        bairro = context.getString(
                            R.string.bairro_address, addressResult.data.address.bairro),
                        cidade = context.getString(
                            R.string.cidade_address, addressResult.data.address.cidade),
                        estado = context.getString(
                            R.string.estado_address, addressResult.data.address.estado)
                    )
                )
            }
        }
    }
}
