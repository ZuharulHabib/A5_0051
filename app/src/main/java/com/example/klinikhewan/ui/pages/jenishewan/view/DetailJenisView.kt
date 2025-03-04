package com.example.klinikhewan.ui.pages.jenishewan.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.klinikhewan.ui.pages.pasien.view.OnError
import com.example.klinikhewan.ui.pages.pasien.view.OnLoading

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
                shape = RoundedCornerShape(50),
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Jenis"
                )
            }
        }
    ) { innerPadding ->
        DetailJenisStatus(
            modifier = Modifier.padding(innerPadding),
            detailJnsUiState = viewModel.jenisDetailState,
            retryAction = { viewModel.getJenisbyId() }
        )
    }
}

@Composable
fun DetailJenisStatus(
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
                ) {
                    Text(
                        "Data tidak ditemukan.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                ItemDetailJenis(
                    jenis = detailJnsUiState.jenis, modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailJnsUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailJenis(
    modifier: Modifier = Modifier,
    jenis: Jenis
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF0F0),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailJenis(judul = "Id Jenis", isinya = jenis.idjenishewan, icon = Icons.Default.Info)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailJenis(judul = "Nama Jenis", isinya = jenis.namajenishewan, icon = Icons.Default.FavoriteBorder)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ComponentDetailJenis(judul = "Deskripsi", isinya = jenis.deskripsi, icon = Icons.Default.MailOutline)
        }
    }
}

@Composable
fun ComponentDetailJenis(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = judul,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = isinya,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}