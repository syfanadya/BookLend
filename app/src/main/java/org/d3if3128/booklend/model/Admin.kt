package org.d3if3128.booklend.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val idadmin : Long = 0L,
    val email: String,
    val password: String,
)
