package com.example.klinikhewan.ui

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.klinikhewan.KlinikHewanApplication
import com.example.klinikhewan.ui.pages.HomeJenis.viewmodel.HomeJenisViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.DetailDokterViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.HomeDokterViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.InsertDokterViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.UpdateDokterViewModel
import com.example.klinikhewan.ui.pages.jenishewan.viewmodel.DetailJenisViewModel
import com.example.klinikhewan.ui.pages.jenishewan.viewmodel.InsertJnsViewModel
import com.example.klinikhewan.ui.pages.jenishewan.viewmodel.UpdateJenisViewModel
import com.example.klinikhewan.ui.pages.pasien.viewmodel.DetailViewModel
import com.example.klinikhewan.ui.pages.pasien.viewmodel.HomeViewModel
import com.example.klinikhewan.ui.pages.pasien.viewmodel.InsertPsnViewModel
import com.example.klinikhewan.ui.pages.pasien.viewmodel.UpdateViewModel
import com.example.klinikhewan.ui.pages.perawatan.viewmodel.HomePerawatanViewModel // Tambahkan import ini

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKlinikHewan().container.pasienRepository)
        }
        initializer {
            HomeJenisViewModel(aplikasiKlinikHewan().container.jenisRepository)
        }
        initializer {
            HomeDokterViewModel(aplikasiKlinikHewan().container.dokterRepository)
        }
        initializer {
            InsertPsnViewModel(aplikasiKlinikHewan().container.pasienRepository)
        }
        initializer {
            InsertJnsViewModel(aplikasiKlinikHewan().container.jenisRepository)
        }
        initializer {
            InsertDokterViewModel(aplikasiKlinikHewan().container.dokterRepository)
        }
        initializer {
            HomePerawatanViewModel(aplikasiKlinikHewan().container.perawatanRepository) // Tambahkan baris ini
        }
        initializer {
            DetailJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.jenisRepository
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.pasienRepository
            )
        }
        initializer {
            DetailDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.dokterRepository
            )
        }
        initializer {
            UpdateViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.pasienRepository
            )
        }
        initializer {
            UpdateJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.jenisRepository
            )
        }
        initializer {
            UpdateDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinikHewan().container.dokterRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKlinikHewan(): KlinikHewanApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KlinikHewanApplication)
