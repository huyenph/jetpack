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
    fun getAppId(id: String): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Completable

    @Query("DELETE FROM users")
    fun deleteAllUsers()
}