package org.d3if3128.booklend.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3128.booklend.model.Buku

class MainViewModelBuku: ViewModel() {

    val databuku = getDataBukuDummy()

    private fun getDataBukuDummy(): List<Buku>{
        val databuku = mutableListOf<Buku>()
        for (i in 29 downTo 20){
            databuku.add(
                Buku(
                    i.toLong(),
                    "Edukasi",
                    10,
                    "ini gambar buku $i",
                    "Graphic Desain $i",
                    "David Rainfurt",
                    "A New Program for Graphic Design is the first communication-design " +
                            "textbook expressly of and for the 21st century.",
                    "2010"
                )
            )
        }
        return databuku
    }

    fun cariBuku(query: String): List<Buku> {
        return databuku.filter { buku ->
            buku.judulbuku.contains(query, ignoreCase = true) ||
                    buku.genrebuku.contains(query, ignoreCase = true)
        }
    }

}