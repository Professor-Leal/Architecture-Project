package com.rafaelleal.android.data_remote.networking.address

import com.rafaelleal.android.domain.entity.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("ws/{cep}/json/")
    suspend fun getAddress( @Path("cep") cep: String ) : AddressApiModel

}

