package org.d3if3128.booklend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.User

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Buat tabel baru untuk entitas User
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `user` (" +
                    "`iduser` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`email` TEXT NOT NULL, " +
                    "`password` TEXT NOT NULL, " +
                    "`tanggalbuatakun` TEXT NOT NULL)"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Tambahkan kolom baru ke tabel user yang sudah ada
        db.execSQL("ALTER TABLE `user` ADD COLUMN `namalengkap` TEXT NOT NULL DEFAULT ''")
        db.execSQL("ALTER TABLE `user` ADD COLUMN `nohp` TEXT NOT NULL DEFAULT ''")
        db.execSQL("ALTER TABLE `user` ADD COLUMN `usia` TEXT NOT NULL DEFAULT ''")
    }
}

@Database(entities = [Buku::class, User::class], version = 3, exportSchema = false)
abstract class BooklendDb : RoomDatabase() {
    abstract val dao: BooklendDao
    companion object {
        @Volatile
        private var INSTANCE: BooklendDb? = null

        fun getInstance(context: Context): BooklendDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooklendDb::class.java,
                        "booklend.db"
                    )
                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)  // Tambahkan migrasi di sini
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
