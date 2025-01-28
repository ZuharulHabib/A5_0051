package com.example.klinikhewan.ui.pages.pasien.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klinikhewan.model.Pasien
import com.example.klinikhewan.repository.PasienRepository
import kotlinx.coroutines.launch


class InsertPsnViewModel( private val psn: PasienRepository): ViewModel(){
    var uiStatePsn by mutableStateOf(InsertPsnUiState())
        private set

    fun updateInsertPsnState(insertPsnUiEvent: InsertPsnUiEvent){
        uiStatePsn = InsertPsnUiState(
            insertPsnUiEvent = insertPsnUiEvent
        )
    }

    suspend fun insertPsn(){
        viewModelScope.launch {
            try {
                psn.insertPasien(uiStatePsn.insertPsnUiEvent.toPasien())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertPsnUiState(
    val insertPsnUiEvent: InsertPsnUiEvent = InsertPsnUiEvent()
)

data class InsertPsnUiEvent(
    val idhewan: String = "",
    val namahewan: String = "",
    val jenishewanid: String = "",
    val pemilik: String = "",
    val kontakpemilik: String = "",
    val tanggallahir: String = "",
    val catatankesehatan: String = ""
)

fun InsertPsnUiEvent.toPasien(): Pasien = Pasien(
    idhewan = idhewan,
    namahewan = namahewan,
    jenishewanid = jenishewanid,
    pemilik = pemilik,
    kontakpemilik = kontakpemilik,
    tanggallahir = tanggallahir,
    catatankesehatan = catatankesehatan
)

fun Pasien.toInsertPsnUiEvent(): InsertPsnUiEvent = InsertPsnUiEvent(
    idhewan = idhewan,
    namahewan = namahewan,
    jenishewanid = jenishewanid,
    pemilik = pemilik,
    kontakpemilik = kontakpemilik,
    tanggallahir = tanggallahir,
    catatankesehatan = catatankesehatan
)

fun Pasien.toUiStatePsn(): InsertPsnUiState = InsertPsnUiState(
    insertPsnUiEvent = toInsertPsnUiEvent()
)

