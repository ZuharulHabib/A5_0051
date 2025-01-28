package com.example.klinikhewan.ui.pages.dokter.viewmodel

import com.example.klinikhewan.ui.navigation.UpdateDokter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klinikhewan.repository.DokterRepository
import kotlinx.coroutines.launch

class UpdateDokterViewModel(
    savedStateHandle: SavedStateHandle,
    private val dokterRepository: DokterRepository
) : ViewModel() {
    var updateDktrUiState by mutableStateOf(InsertDktrUiState())
        private set

    private val _idDokter: String = checkNotNull(savedStateHandle[UpdateDokter.idDOKTER])

    init {
        viewModelScope.launch {
            updateDktrUiState = dokterRepository.getDokterById(_idDokter)
                .toUiStateDktr()
        }
    }

    fun updateInsertDktrState(insertDktrUiEvent: InsertDktrUiEvent) {
        updateDktrUiState = InsertDktrUiState(
            insertDktrUiEvent = insertDktrUiEvent
        )
    }

    suspend fun updateDokter() {
        viewModelScope.launch {
            try {
                dokterRepository.updateDokter(_idDokter, updateDktrUiState.insertDktrUiEvent.toDokter())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
