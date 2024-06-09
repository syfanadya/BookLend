package org.d3if3128.booklend.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.ui.screen.DetailViewModelBuku
import org.d3if3128.booklend.ui.screen.DetailViewModelPeminjaman
import org.d3if3128.booklend.ui.screen.MainViewModelBuku
import org.d3if3128.booklend.ui.screen.MainViewModelPeminjaman

class ViewModelFactoryPeminjaman (
    private val dao: BooklendDao
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModelPeminjaman::class.java)){
            return MainViewModelPeminjaman(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModelPeminjaman::class.java)){
            return DetailViewModelPeminjaman(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}