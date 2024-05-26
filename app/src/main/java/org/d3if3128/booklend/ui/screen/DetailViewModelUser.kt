package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModelUser(private val dao: BooklendDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(email: String, password: String){
        val user =  User(
            email = email,
            password = password,
            tanggalbuatakun = formatter.format(Date())
        )
        viewModelScope.launch (Dispatchers.IO ) {
            dao.insert(user)
        }
    }

    suspend fun getUser(iduser: Long): User? {
        return dao.getUserById(iduser)
    }
    suspend fun isEmailRegistered(email: String): Boolean {
        return dao.getUserByEmail(email) != null
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        val user = dao.getUserByEmail(email)
        return if (user != null && user.password == password) user else null
    }

}