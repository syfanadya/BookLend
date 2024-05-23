package org.d3if3128.booklend.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val iduser: Long = 0L,
    val roleuser: String,
    val potoprofile: String,
    val namauser: String,
    val email: String,
    val nomorhp: String,
    val password: String,
    val tanggalpinjam : String,
    val tanggalupdate : String
)
