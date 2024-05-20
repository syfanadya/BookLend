package org.d3if3128.booklend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3128.booklend.model.Buku

@Database(entities = [Buku::class], version = 1, exportSchema = false)
abstract class BooklendDb : RoomDatabase() {
    abstract val dao: BooklendDao
    companion object{
        @Volatile
        private var INSTANCE: BooklendDb? = null

        fun getInstance(context: Context): BooklendDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooklendDb::class.java,
                        "booklend.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}