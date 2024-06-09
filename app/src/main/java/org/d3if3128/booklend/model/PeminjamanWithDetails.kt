package org.d3if3128.booklend.model

import androidx.room.Embedded
import androidx.room.Relation

data class PeminjamanWithDetails(
    @Embedded val peminjaman: Peminjaman,
    @Relation(
        parentColumn = "idbuku",
        entityColumn = "idbuku"
    )
    val buku: Buku,
    @Relation(
        parentColumn = "iduser",
        entityColumn = "iduser"
    )
    val user: User
)

