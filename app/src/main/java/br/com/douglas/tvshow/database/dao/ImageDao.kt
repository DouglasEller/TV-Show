package br.com.douglas.tvshow.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.douglas.tvshow.network.vo.Image

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image)

    @Query("SELECT * from Image where tvShowId = :id")
    fun findById(id: Long) : Image
}