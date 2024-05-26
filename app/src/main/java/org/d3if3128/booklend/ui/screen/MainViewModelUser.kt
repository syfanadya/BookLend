package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.User

class MainViewModelUser(dao: BooklendDao) : ViewModel() {

    val datauser: StateFlow<List<User>> = dao.getUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0L),
        initialValue = emptyList()
    )

}