package org.d3if3128.booklend.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.Buku

class DetailViewModelBuku(private val dao: BooklendDao): ViewModel() {

    fun insert(
        gambarbuku: String,
        judulbuku: String,
        genrebuku: String,
        penulisbuku: String,
        tahunterbit: String,
        jumlahbuku: Int,
        deskripsibuku: String
    ){
        val buku = Buku(
            gambarbuku = gambarbuku,
            judulbuku = judulbuku,
            genrebuku = genrebuku,
            penulisbuku = penulisbuku,
            tahunterbit = tahunterbit,
            jumlahbuku = jumlahbuku,
            deskripsibuku = deskripsibuku
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(buku)
        }
    }
    suspend fun getBuku(idbuku: Long): Buku? {
        return dao.getBukuById(idbuku)
    }

    fun update(
        idbuku: Long,
        gambarbuku: String,
        judulbuku: String,
        genrebuku: String,
        penulisbuku: String,
        tahunterbit: String,
        jumlahbuku: Int,
        deskripsibuku: String
    ){
        val buku = Buku(
            idbuku = idbuku,
            gambarbuku = gambarbuku,
            judulbuku = judulbuku,
            genrebuku = genrebuku,
            penulisbuku = penulisbuku,
            tahunterbit = tahunterbit,
            jumlahbuku = jumlahbuku,
            deskripsibuku = deskripsibuku
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(buku)
        }
    }

    fun delete(idbuku: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val buku = dao.getBukuById(idbuku)
            if (buku != null) {
                dao.deleteById(idbuku)
                Log.d("DetailViewModelBuku", "Buku dengan ID: $idbuku berhasil dihapus")
            } else {
                Log.d("DetailViewModelBuku", "Buku dengan ID: $idbuku tidak ditemukan")
            }
        }
    }
}