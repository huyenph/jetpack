package com.utildev.jetpack.data.local.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "userId")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userName")
    val userName: String
)