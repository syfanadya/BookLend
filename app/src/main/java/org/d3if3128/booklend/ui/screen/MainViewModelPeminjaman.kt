package org.d3if3128.booklend.ui.screen

import androidx.compose.ui.graphics.Path.Companion.combine
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.Peminjaman
import org.d3if3128.booklend.model.User
data class PeminjamanWithDetails(
    val peminjaman: Peminjaman,
    val buku: Buku,
    val user: User
)

class MainViewModelPeminjaman(dao: BooklendDao) : ViewModel() {

    val datapeminjaman: StateFlow<List<PeminjamanWithDetails>> = combine(
        dao.getPeminjaman(),
        dao.getBuku(),
        dao.getUser()
    ) { peminjamanList, bukuList, userList ->
        peminjamanList.map { peminjaman ->
            val buku = bukuList.find { it.idbuku == peminjaman.idbuku }!!
            val user = userList.find { it.iduser == peminjaman.iduser }!!
            PeminjamanWithDetails(peminjaman, buku, user)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

}