package com.example.klinikhewan.ui.pages.perawatan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klinikhewan.model.Dokter
import com.example.klinikhewan.model.Pasien
import com.example.klinikhewan.model.Perawatan
import com.example.klinikhewan.repository.DokterRepository
import com.example.klinikhewan.repository.PasienRepository
import com.example.klinikhewan.repository.PerawatanRepository
import kotlinx.coroutines.launch

class InsertPrwtnViewModel(
    private val psn: PasienRepository,
    private val dktr: DokterRepository,
    private val prwtn: PerawatanRepository
): ViewModel(){
    var uiStatePrwtn by mutableStateOf(InsertPrwtnUiState())
        private set

    var listhewan by mutableStateOf(listOf<Pasien>())
        private set

    var listdokter by mutableStateOf(listOf<Dokter>())
        private set

    init {
        LoadDataList()
    }

    fun updateInsertPrwtnState(insertUiPrwtnEvent: InsertUiPrwtnEvent){
        uiStatePrwtn = InsertPrwtnUiState(
            insertUiPrwtnEvent = insertUiPrwtnEvent
        )
    }

    suspend fun insertPrwtn(){
        viewModelScope.launch {
            try {
                prwtn.insertPerawatan(uiStatePrwtn.insertUiPrwtnEvent.toPrwtn())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun LoadDataList(){
        viewModelScope.launch {
            try {
                listhewan = psn.getAllPasien().data
                listdokter = dktr.getAllDokter().data
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertPrwtnUiState(
    val insertUiPrwtnEvent: InsertUiPrwtnEvent = InsertUiPrwtnEvent()
)

data class InsertUiPrwtnEvent(
    val idperawatan: String = "",
    val idhewan: String = "",
    val iddokter: String = "",
    val tanggalperawatan: String = "",
    val detailperawatan: String = ""
)

fun InsertUiPrwtnEvent.toPrwtn(): Perawatan = Perawatan(
    idperawatan = idperawatan,
    idhewan = idhewan,
    iddokter = iddokter,
    tanggalperawatan = tanggalperawatan,
    detailperawatan = detailperawatan
)

fun Perawatan.toUiStatePrwtn(): InsertPrwtnUiState = InsertPrwtnUiState(
    insertUiPrwtnEvent = toInsertUiPrwtnEvent()
)

fun Perawatan.toInsertUiPrwtnEvent(): InsertUiPrwtnEvent = InsertUiPrwtnEvent(
    idperawatan = idperawatan,
    idhewan = idhewan,
    iddokter = iddokter,
    tanggalperawatan = tanggalperawatan,
    detailperawatan = detailperawatan
)