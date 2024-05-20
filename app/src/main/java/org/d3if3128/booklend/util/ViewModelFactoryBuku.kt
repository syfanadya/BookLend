package org.d3if3128.booklend.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.ui.screen.DetailViewModelBuku
import org.d3if3128.booklend.ui.screen.MainViewModelBuku

class ViewModelFactoryBuku (
    private val dao: BooklendDao
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModelBuku::class.java)){
            return MainViewModelBuku(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModelBuku::class.java)){
            return DetailViewModelBuku(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}