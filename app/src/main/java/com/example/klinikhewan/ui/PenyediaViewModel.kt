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
            HomeViewModel(aplikasiKlinik().container.pasienRepository)
        }
        initializer {
            HomeJenisViewModel(aplikasiKlinik().container.jenisRepository)
        }
        initializer {
            HomeDokterViewModel(aplikasiKlinik().container.dokterRepository)
        }
        initializer {
            HomePerawatanViewModel(aplikasiKlinik().container.perawatanRepository)
        }
        initializer {
            InsertPsnViewModel(
                aplikasiKlinik().container.pasienRepository,
                aplikasiKlinik().container.jenisRepository
            )
        }
        initializer {
            InsertPrwtnViewModel(
                aplikasiKlinik().container.pasienRepository,
                aplikasiKlinik().container.dokterRepository,
                aplikasiKlinik().container.perawatanRepository
            )
        }
        initializer {
            InsertJnsViewModel(aplikasiKlinik().container.jenisRepository)
        }
        initializer {
            InsertDokterViewModel(aplikasiKlinik().container.dokterRepository)
        }
        initializer {
            DetailJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.jenisRepository
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.pasienRepository
            )
        }
        initializer {
            DetailDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.dokterRepository
            )
        }
        initializer {
            UpdateViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.pasienRepository,
            )
        }
        initializer {
            UpdateJenisViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.jenisRepository
            )
        }
        initializer {
            UpdateDokterViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.dokterRepository
            )
        }
        initializer {
            UpdatePerawatanViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.pasienRepository,
                aplikasiKlinik().container.perawatanRepository,
                aplikasiKlinik().container.dokterRepository
            )
        }
        initializer {
            DetailPerawatanViewModel(
                createSavedStateHandle(),
                aplikasiKlinik().container.perawatanRepository
            )
        }
    }
}


fun CreationExtras.aplikasiKlinik(): KlinikHewanApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KlinikHewanApplication)
