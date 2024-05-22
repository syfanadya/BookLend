package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.Buku

class MainViewModelBuku(dao: BooklendDao): ViewModel() {

    val databuku: StateFlow<List<Buku>> = dao.getBuku().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0L),
        initialValue = emptyList()
    )

    private val _searchText= MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    @OptIn(FlowPreview::class)
    val _databuku = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(databuku){text, _databuku ->
            if(text.isBlank()){
                _databuku
            } else{
                _databuku.filter {
                    it.doesMactchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(0),
            databuku.value
        )

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}