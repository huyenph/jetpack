package com.utildev.jetpack.data.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.utildev.jetpack.data.local.persistence.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AppDao {
    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getAppId(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}