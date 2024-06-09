package org.d3if3128.booklend.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.Peminjaman
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.ui.screen.PeminjamanWithDetails

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

    @Query("SELECT * FROM user WHERE iduser = :iduser")
    suspend fun getUserById(iduser: Long): User?

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM user ORDER BY tanggalbuatakun")
    fun getUser(): Flow<List<User>>

    @Insert
    suspend fun insert(peminjaman: Peminjaman)

    @Update
    suspend fun update(peminjaman: Peminjaman)

    @Query("SELECT * FROM peminjaman ORDER BY idpeminjaman DESC")
    fun getPeminjaman(): Flow<List<Peminjaman>>

    @Transaction
    @Query("SELECT * FROM peminjaman WHERE idpeminjaman = :idpeminjaman")
    suspend fun getPeminjamanById(idpeminjaman: Long): org.d3if3128.booklend.model.PeminjamanWithDetails?

    @Query("UPDATE Peminjaman SET status = :status, tanggalkembali = " +
            ":tanggalKembali WHERE idpeminjaman = :idpeminjaman")
    suspend fun updatePeminjamanStatus(idpeminjaman: Long, status: String, tanggalKembali: String?)

}