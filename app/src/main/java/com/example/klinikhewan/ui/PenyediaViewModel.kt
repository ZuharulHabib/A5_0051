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
import com.example.klinikhewan.ui.pages.perawatan.viewmodel.DetailPerawatanViewModel
import com.example.klinikhewan.ui.pages.perawatan.viewmodel.HomePerawatanViewModel
import com.example.klinikhewan.ui.pages.perawatan.viewmodel.InsertPrwtnViewModel
import com.example.klinikhewan.ui.pages.perawatan.viewmodel.UpdatePerawatanViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKlinikGw().container.pasienRepository)
        }
        initializer {
            HomeJenisViewModel(aplikasiKlinikGw().container.jenisRepository)
        }
        initializer {
            HomeDokterViewModel(aplikasiKlinikGw().container.dokterRepository)
        }
        initializer {
            HomePerawatanViewModel(aplikasiKlinikGw().container.perawatanRepository)
        }
        initializer {
            InsertPsnViewModel(
                aplikasiKlinikGw().container.pasienRepository,
                aplikasiKlinikGw().container.jenisRepository
            )
        }
        initializer {
            InsertPrwtnViewModel(
                aplikasiKlinikGw().container.pasienRepository,
                aplikasiKlinikGw().container.dokterRepository,
                aplikasiKlinikGw().container.perawatanRepository
            )
        }
        initializer {
            InsertJnsViewModel(aplikasiKlinikGw().container.jenisRepository)
        }
        initializer {
            InsertDokterViewModel(aplikasiKlinikGw().container.dokterRepository)
        }
        initializer {
            DetailJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.jenisRepository
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.pasienRepository
            )
        }
        initializer {
            DetailDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.dokterRepository
            )
        }
        initializer {
            UpdateViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.pasienRepository,
            )
        }
        initializer {
            UpdateJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.jenisRepository
            )
        }
        initializer {
            UpdateDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.dokterRepository
            )
        }
        initializer {
            UpdatePerawatanViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.pasienRepository,
                aplikasiKlinikGw().container.perawatanRepository,
                aplikasiKlinikGw().container.dokterRepository
            )
        }
        initializer {
            DetailPerawatanViewModel(
                createSavedStateHandle(),
                aplikasiKlinikGw().container.perawatanRepository
            )
        }
    }
}


fun CreationExtras.aplikasiKlinikGw(): KlinikHewanApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KlinikHewanApplication)
