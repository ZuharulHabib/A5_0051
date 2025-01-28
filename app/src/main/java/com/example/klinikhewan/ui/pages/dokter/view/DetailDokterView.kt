package com.example.klinikhewan.ui.pages.dokter.view

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
import com.example.klinikhewan.model.Dokter
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.customwidget.CostumeTopAppBar
import com.example.klinikhewan.ui.navigation.DestinasiDetailDokter
import com.example.klinikhewan.ui.pages.dokter.viewmodel.DetailDokterViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.DetailDktrUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDokterScreen(
    navigateBack: () -> Unit,
    navigateToItemUpdateDokter: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailDokter.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getDokterById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemUpdateDokter,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Dokter"
                )
            }
        }
    ) { innerPadding ->
        DetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailDktrUiState = viewModel.dokterDetailState,
            retryAction = { viewModel.getDokterById() }
        )
    }
}

@Composable
fun DetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailDktrUiState: DetailDktrUiState
) {
    when (detailDktrUiState) {
        is DetailDktrUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailDktrUiState.Success -> {
            if (detailDktrUiState.dokter.iddokter.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan.") }
            } else {
                ItemDetailDokter(
                    dokter = detailDktrUiState.dokter, modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailDktrUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailDokter(
    modifier: Modifier = Modifier,
    dokter: Dokter
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailDokter(judul = "ID Dokter", isinya = dokter.iddokter)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailDokter(judul = "Nama Dokter", isinya = dokter.namadokter)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailDokter(judul = "Spesialisasi", isinya = dokter.spesialisasi)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailDokter(judul = "Kontak", isinya = dokter.kontak)
        }
    }
}

@Composable
fun ComponentDetailDokter(
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