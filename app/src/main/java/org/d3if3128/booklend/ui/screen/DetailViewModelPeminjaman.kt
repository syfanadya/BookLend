package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.Peminjaman
import org.d3if3128.booklend.model.PeminjamanWithDetails
import org.d3if3128.booklend.model.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModelPeminjaman(private val dao: BooklendDao) : ViewModel() {
    fun insert(
        iduser: Long,
        idbuku: Long,
        status: String,
        tanggalkembali: String?
    ){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val tanggalpinjam = dateFormat.format(Date())

        val peminjaman = Peminjaman(
            iduser = iduser,
            idbuku = idbuku,
            status = status,
            tanggalpinjam = tanggalpinjam,
            tanggalkembali = tanggalkembali,
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(peminjaman)
        }
    }

    suspend fun getPeminjaman(idpeminjaman: Long): PeminjamanWithDetails?{
        return withContext(Dispatchers.IO) {
            dao.getPeminjamanById(idpeminjaman)
        }
    }

    fun updatePeminjamanStatus(idpeminjaman: Long, status: String, tanggalKembali: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updatePeminjamanStatus(idpeminjaman, status, tanggalKembali)
        }
    }

}