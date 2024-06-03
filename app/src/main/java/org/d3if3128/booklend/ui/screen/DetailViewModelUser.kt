package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3128.booklend.database.BooklendDao
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.network.UserDataStore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModelUser(private val dao: BooklendDao,  private val userDataStore: UserDataStore) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(
        namalengkap: String,
        nohp: String,
        usia: String,
        email: String,
        password: String
    ){
        val user =  User(
            namalengkap = namalengkap,
            nohp = nohp,
            usia = usia,
            email = email,
            password = password,
            tanggalbuatakun = formatter.format(Date())
        )
        viewModelScope.launch (Dispatchers.IO ) {
            dao.insert(user)
        }
    }

    suspend fun getUser(email: String): User? {
        return dao.getUserByEmail(email)
    }

    suspend fun getUser2(iduser: Long): User? {
        return dao.getUserById(iduser)
    }
    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(user)
            userDataStore.saveData(user)  // Ensure this updates the DataStore
        }
    }

    suspend fun isEmailRegistered(email: String): Boolean {
        return dao.getUserByEmail(email) != null
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        val user = dao.getUserByEmail(email)
        return if (user != null && user.password == password) user else null
    }

}