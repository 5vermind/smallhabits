package com.h2ve.smallhabits.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.h2ve.smallhabits.db.SmallHabitsDatabase

//@Entity(tableName = SmallHabitsDatabase.TABLE_HABITS)
//data class Habit (
//    @PrimaryKey(autoGenerate = true)
//    var _id: Int,
//    var what: String,
//    var _when: String,
//    var where: String,
//    var rating: Int,
//    var donDate: String,
//    var nextHabit: Int
//)

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)