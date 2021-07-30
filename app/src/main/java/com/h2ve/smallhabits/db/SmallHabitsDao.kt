package com.h2ve.smallhabits.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.h2ve.smallhabits.model.RemoteKeys

@Dao
interface HabitsDao {

//    @Query("SELECT * FROM habits_data ORDER BY habits_data._id DESC")
//    fun observeBoardsPaginated(): PagingSource<Int, Habits> //TODO Change PagingSource -> RemoteMediator
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(data: ArrayList<HabitsData>)
//
//    @Query("DELETE FROM habits_data")
//    fun deleteBoardItems(): Int

}

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE repoId = :repoId")
    suspend fun remoteKeysRepoId(repoId: Int): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}