package com.rafaelleal.android.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafaelleal.android.data_local.db.address.AddressDao
import com.rafaelleal.android.data_local.db.address.AddressEntity


@Database(entities = [AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}