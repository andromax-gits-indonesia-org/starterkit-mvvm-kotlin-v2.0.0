package id.co.gits.gitsutils.data.source.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import id.co.gits.gitsutils.data.model.Movie
import id.co.gits.gitsutils.data.source.local.movie.MovieDao

/**
 * Created by radhikayusuf on 17/05/19.
 */

@Database(entities = [(Movie::class)], version = 3)
abstract class GitsAppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: GitsAppDatabase? = null

        fun getInstance(context: Context): GitsAppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        GitsAppDatabase::class.java, "Movies.db")
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_3_4)
                        .build()

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE movie ADD COLUMN last_update INTEGER")
            }
        }
    }
}