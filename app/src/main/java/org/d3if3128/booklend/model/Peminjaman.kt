package org.d3if3128.booklend.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "peminjaman",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["iduser"],
            childColumns = ["iduser"]
        ),
        ForeignKey(
            entity = Buku::class,
            parentColumns = ["idbuku"],
            childColumns = ["idbuku"],
        )
    ],

    indices = [Index(value = ["iduser"]), Index(value = ["idbuku"])]
)
data class Peminjaman(
    @PrimaryKey(autoGenerate = true)
    val idpeminjaman: Long = 0L,
    val iduser: Long,
    val idbuku: Long,
    val status: String,
    val tanggalpinjam: String,
    val tanggalkembali: String?
)
