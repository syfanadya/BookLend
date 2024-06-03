package org.d3if3128.booklend.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.network.UserDataStore
import org.d3if3128.booklend.ui.screen.DetailViewModelUser
import org.d3if3128.booklend.ui.screen.MainViewModelUser

class ViewModelFactoryUser (
    private val dao: BooklendDao,
    private val userDataStore: UserDataStore
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModelUser::class.java)){
            return MainViewModelUser(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModelUser::class.java)){
            return DetailViewModelUser(dao, userDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}