package org.d3if3128.booklend.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku")
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val idbuku: Long = 0L,
    val genrebuku: String,
    val jumlahbuku: Int,
    val gambarbuku: String,
    val judulbuku: String,
    val penulisbuku: String,
    val deskripsibuku: String,
    val tahunterbit: String
)
