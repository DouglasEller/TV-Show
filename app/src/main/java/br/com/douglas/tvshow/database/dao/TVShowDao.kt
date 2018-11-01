package br.com.douglas.tvshow.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.douglas.tvshow.network.vo.TVShowsResponse

@Dao
interface TVShowDao {

    @Query("SELECT * from TVShowsResponse")
    fun getAll(): List<TVShowsResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShowsResponse: TVShowsResponse)

    @Query("DELETE from TVShowsResponse where id = :id")
    fun deleteById(id: Long)
}