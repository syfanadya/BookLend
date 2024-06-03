package org.d3if3128.booklend.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.d3if3128.booklend.model.User

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "user_preference"
)

class UserDataStore(private val context: Context) {
    companion object{
        private val USER_ID = longPreferencesKey("iduser")
        private val USER_FULLNAME = stringPreferencesKey("namalengkap")
        private val USER_NOPHONE = stringPreferencesKey("nohp")
        private val USER_AGE = stringPreferencesKey("usia")
        private val USER_EMAIL = stringPreferencesKey("email")
        private val USER_PASSWORD = stringPreferencesKey("password")
        private val USER_MAKEACCOUT = stringPreferencesKey("makeaccount")
    }

    val userFlow: Flow<User> = context.dataStore.data.map { preferences ->
        User(
            iduser = preferences[USER_ID]?.toLong() ?: 0L,
            namalengkap = preferences[USER_FULLNAME] ?: "",
            nohp = preferences[USER_NOPHONE] ?: "",
            usia = preferences[USER_AGE] ?: "",
            email = preferences[USER_EMAIL] ?: "",
            password = preferences[USER_PASSWORD] ?: "",
            tanggalbuatakun = preferences[USER_MAKEACCOUT] ?: "",
        )
    }


    suspend fun saveData(user: User){
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = user.iduser
            preferences[USER_FULLNAME] = user.namalengkap
            preferences[USER_NOPHONE] = user.nohp
            preferences[USER_AGE] = user.usia
            preferences[USER_EMAIL] = user.email
            preferences[USER_PASSWORD] = user.password
            preferences[USER_MAKEACCOUT] = user.tanggalbuatakun
        }
    }
}