package br.com.douglas.tvshow.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.douglas.tvshow.database.dao.ImageDao
import br.com.douglas.tvshow.database.dao.TVShowDao
import br.com.douglas.tvshow.network.vo.Image
import br.com.douglas.tvshow.network.vo.TVShowsResponse

@Database(entities = [(TVShowsResponse::class), (Image::class)], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun tvShowDao(): TVShowDao
    abstract fun imageDao(): ImageDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDataBase::class.java, "tvshow.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}