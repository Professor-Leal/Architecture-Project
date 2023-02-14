package com.rafaelleal.android.domain.usecase

import com.rafaelleal.android.domain.entity.Address
import com.rafaelleal.android.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllAddressesUseCase  @Inject constructor(
    configuration: UseCase.Configuration,
    private val addressRepository: AddressRepository
) : UseCase<
        GetAllAddressesUseCase.Request,
        GetAllAddressesUseCase.Response
        >(configuration)
{
    override fun process(
        request: Request
    ): Flow<Response> =
        addressRepository.getAllAddresses()
            .map { _address ->
                Response(_address)
            }

    object Request : UseCase.Request
    data class Response(val addresses: List<Address>) : UseCase.Response
}
