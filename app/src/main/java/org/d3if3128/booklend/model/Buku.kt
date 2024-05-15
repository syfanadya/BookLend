package org.d3if3128.booklend.model

data class Buku(
    val idbuku: Long,
    val genrebuku: String,
    val jumlahbuku: Int,
    val gambarbuku: String,
    val judulbuku: String,
    val penulisbuku: String,
    val deskripsibuku: String,
    val tahunterbit: String
)
