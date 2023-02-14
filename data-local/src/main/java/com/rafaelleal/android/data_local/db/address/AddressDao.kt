package com.rafaelleal.android.data_local.db.address

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelleal.android.domain.entity.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Query("SELECT * FROM address WHERE cep = :cep")
    fun getAddress(cep: String): Flow<AddressEntity>

    @Query("SELECT * FROM address")
    fun getAllAddresses(): Flow<List<AddressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAddress(addressEntity: AddressEntity)



}

