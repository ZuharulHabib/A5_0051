package com.example.klinikhewan.ui.pages.dokter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.klinikhewan.model.Dokter
import com.example.klinikhewan.repository.DokterRepository
import com.example.klinikhewan.ui.navigation.DestinasiDetailDokter
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailDktrUiState {
    data class Success(val dokter: Dokter) : DetailDktrUiState()
    object Error : DetailDktrUiState()
    object Loading : DetailDktrUiState()
}

class DetailDokterViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepository: DokterRepository
) : ViewModel() {

    var dokterDetailState: DetailDktrUiState by mutableStateOf(DetailDktrUiState.Loading)
        private set

    private val _idDokter: String = checkNotNull(savedStateHandle[DestinasiDetailDokter.idDOKTER])

    init {
        getDokterById()
    }

    fun getDokterById() {
        viewModelScope.launch {
            dokterDetailState = DetailDktrUiState.Loading
            dokterDetailState = try {
                val dokter = dokterRepository.getDokterById(_idDokter)
                DetailDktrUiState.Success(dokter)
            } catch (e: IOException) {
                DetailDktrUiState.Error
            } catch (e: HttpException) {
                DetailDktrUiState.Error
            }
        }
    }
}