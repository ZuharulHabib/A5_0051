package com.example.klinikhewan.ui.pages.pasien.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klinikhewan.repository.PasienRepository
import com.example.klinikhewan.ui.navigation.UpdatePasien
import kotlinx.coroutines.launch

class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val psn: PasienRepository
): ViewModel(){
    var updatePsnUiState by mutableStateOf(InsertPsnUiState())
        private set

    private val _idhewan: String = checkNotNull(savedStateHandle[UpdatePasien.idPASIEN])

    init {
        viewModelScope.launch {
            updatePsnUiState = psn.getPasienById(_idhewan)
                .toUiStatePsn()
        }
    }

    fun updateInsertPsnState(insertPsnUiEvent: InsertPsnUiEvent){
        updatePsnUiState = InsertPsnUiState(
            insertPsnUiEvent = insertPsnUiEvent
        )
    }

    suspend fun updatePasien(){
        viewModelScope.launch {
            try {
                psn.updatePasien(_idhewan, updatePsnUiState.insertPsnUiEvent.toPasien())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}