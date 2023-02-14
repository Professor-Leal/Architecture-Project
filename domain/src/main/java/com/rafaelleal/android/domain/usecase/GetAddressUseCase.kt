package com.rafaelleal.android.domain.usecase

import android.util.Log
import com.rafaelleal.android.domain.entity.Address
import com.rafaelleal.android.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    configuration: UseCase.Configuration,
    private val addressRepository: AddressRepository
) : UseCase<
        GetAddressUseCase.Request,
        GetAddressUseCase.Response
        >(configuration)
{

    val TAG = "Main"
    override fun process(
        request: Request
    ): Flow<Response> =
        addressRepository.getAddressByCep(request.cep)
            .map { _address ->
                Log.i(TAG, "GetAddressUseCase.process"  )
                Response(_address)
            }

    data class Request(val cep: String) : UseCase.Request
    data class Response(val address: Address) : UseCase.Response
}

