package com.rafaelleal.android.data_local.injection

import com.rafaelleal.android.data_local.source.LocalAddressDataSourceImpl
import com.rafaelleal.android.data_repository.data_source.local.LocalAddressDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindAddressDataSource
                (localAddresDataSourceImpl: LocalAddressDataSourceImpl):
            LocalAddressDataSource

}