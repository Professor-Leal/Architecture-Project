package com.rafaelleal.android.data_repository.injection

import com.rafaelleal.android.data_repository.repository.AddressRepositoryImpl
import com.rafaelleal.android.domain.repository.AddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAddressRepository(
        addressRepositoryImpl: AddressRepositoryImpl
    ): AddressRepository

}


