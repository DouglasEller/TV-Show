package br.com.douglas.tvshow.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.douglas.tvshow.network.vo.Externals

@Dao
interface ExternalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(externals: Externals)

    @Query("SELECT * from Externals where tvShowId = :id")
    fun findById(id: Long) : Externals
}