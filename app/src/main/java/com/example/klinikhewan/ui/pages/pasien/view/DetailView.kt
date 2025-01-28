package com.example.klinikhewan.ui.pages.pasien.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.klinikhewan.model.Pasien
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.customwidget.CostumeTopAppBar
import com.example.klinikhewan.ui.navigation.DestinasiNavigasi
import com.example.klinikhewan.ui.navigation.DesttinasiDetailPasien
import com.example.klinikhewan.ui.pages.pasien.viewmodel.DetailPsnUiState
import com.example.klinikhewan.ui.pages.pasien.viewmodel.DetailViewModel

object DestinasiDetailPasien: DestinasiNavigasi {
    override val route ="DetailPasien"
    override val titleRes = "Detail Pasien"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPasien(
    navigateBack: () -> Unit,
    navigateToItemUpdatePsn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    navigateToAddPerawatan: () -> Unit
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DesttinasiDetailPasien.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getPasienbyId()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemUpdatePsn,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak"
                )
            }
        }
    ) { innerPadding ->
        DetailPasienStatus(
            modifier = Modifier.padding(innerPadding),
            detailPsnUiState = viewModel.pasienDetailState,
            retryAction = { viewModel.getPasienbyId() }
        )
    }
}


@Composable
fun DetailPasienStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailPsnUiState: DetailPsnUiState
) {
    when (detailPsnUiState) {
        is DetailPsnUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailPsnUiState.Success -> {
            if (detailPsnUiState.pasien.idhewan.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan.") }
            } else {
                ItemDetailPsn(
                    pasien = detailPsnUiState.pasien, modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailPsnUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun ItemDetailPsn(
    modifier: Modifier = Modifier,
    pasien: Pasien
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailPsn(judul = "Id Hewan", isinya = pasien.idhewan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Nama Hewan", isinya = pasien.namahewan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Jenis Hewan ID", isinya = pasien.jenishewanid)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Pemilik", isinya = pasien.pemilik)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Kontak Pemilik", isinya = pasien.kontakpemilik)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Tgl Lahir", isinya = pasien.tanggallahir)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailPsn(judul = "Catatan Kesehatan", isinya = pasien.catatankesehatan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}


@Composable
fun ComponentDetailPsn(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = judul,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}