package org.d3if3128.booklend.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.User

@Dao
interface BooklendDao {
    @Insert
    suspend fun insert(buku: Buku)

    @Update
    suspend fun update(buku: Buku)

    @Query("SELECT * FROM buku ORDER BY judulbuku ASC")
    fun getBuku(): Flow<List<Buku>>

    @Query("SELECT * FROM buku WHERE idbuku = :idbuku")
    suspend fun getBukuById(idbuku: Long): Buku?

    @Query("DELETE FROM buku WHERE idbuku = :idbuku")
    suspend fun deleteById(idbuku: Long)

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update (user: User)
}