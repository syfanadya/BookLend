package org.d3if3128.booklend.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val iduser: Long = 0L,
    val namalengkap: String,
    val nohp: String,
    val usia: String,
    val email: String,
    val password: String,
    val tanggalbuatakun: String,
)
