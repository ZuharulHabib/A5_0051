package com.example.klinikhewan.ui.pages.jenishewan.view

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
import com.example.klinikhewan.model.Jenis
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.customwidget.CostumeTopAppBar
import com.example.klinikhewan.ui.navigation.DestinasiDetailJenis
import com.example.klinikhewan.ui.pages.jenishewan.viewmodel.DetailJenisViewModel
import com.example.klinikhewan.ui.pages.jenishewan.viewmodel.DetailJnsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJenisScreen(
    navigateBack: () -> Unit,
    navigateToItemUpdateJns: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailJenisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailJenis.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getJenisbyId()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemUpdateJns,
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
        DetailStatus(
            modifier = Modifier.padding(innerPadding),
            detailJnsUiState = viewModel.jenisDetailState,
            retryAction = { viewModel.getJenisbyId() }
        )
    }
}

@Composable
fun DetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailJnsUiState: DetailJnsUiState
) {
    when (detailJnsUiState) {
        is DetailJnsUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailJnsUiState.Success -> {
            if (detailJnsUiState.jenis.idjenishewan.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan.") }
            } else {
                ItemDetailJns(
                    jenis = detailJnsUiState.jenis, modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailJnsUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun ItemDetailJns(
    modifier: Modifier = Modifier,
    jenis: Jenis
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailJns(judul = "Id Jenis", isinya = jenis.idjenishewan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailJns(judul = "Nama Jenis", isinya = jenis.namajenishewan)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailJns(judul = "Alamat", isinya = jenis.deskripsi)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun ComponentDetailJns(
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