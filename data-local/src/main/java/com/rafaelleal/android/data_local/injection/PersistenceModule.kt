package com.rafaelleal.android.data_local.injection

import android.content.Context
import androidx.room.Room
import com.rafaelleal.android.data_local.db.AppDatabase
import com.rafaelleal.android.data_local.db.address.AddressDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

const val DATABASE_NAME = "my-database"

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).build()

    @Provides
    fun provideAddressDao(appDatabase: AppDatabase):
            AddressDao = appDatabase.addressDao()

}