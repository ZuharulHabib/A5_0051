package com.example.klinikhewan.ui.pages.dokter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.customwidget.CostumeTopAppBar
import com.example.klinikhewan.ui.navigation.AddDokter
import com.example.klinikhewan.ui.pages.dokter.viewmodel.InsertDktrUiEvent
import com.example.klinikhewan.ui.pages.dokter.viewmodel.InsertDktrUiState
import com.example.klinikhewan.ui.pages.dokter.viewmodel.InsertDokterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryDokterScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertDokterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = AddDokter.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        // Tambahkan scroll pada level konten utama
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            EntryDokterBody(
                insertDktrUiState = viewModel.uiStateDktr,
                onDokterValueChange = viewModel::updateInsertDktrState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.insertDokter()
                        navigateBack()
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp) // Gunakan padding tambahan hanya di dalam konten
            )
        }
    }
}

@Composable
fun EntryDokterBody(
    insertDktrUiState: InsertDktrUiState,
    onDokterValueChange: (InsertDktrUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
    ) {
        FormInput(
            insertDktrUiEvent = insertDktrUiState.insertDktrUiEvent,
            onValueChange = onDokterValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertDktrUiEvent: InsertDktrUiEvent,
    onValueChange: (InsertDktrUiEvent) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ID Dokter
        InputFieldWithIcon(
            label = "ID Dokter",
            value = insertDktrUiEvent.iddokter,
            onValueChange = { onValueChange(insertDktrUiEvent.copy(iddokter = it)) },
            icon = Icons.Default.Info,
            placeholder = "Masukkan ID dokter",
            enabled = enabled
        )

        // Nama Dokter
        InputFieldWithIcon(
            label = "Nama Dokter",
            value = insertDktrUiEvent.namadokter,
            onValueChange = { onValueChange(insertDktrUiEvent.copy(namadokter = it)) },
            icon = Icons.Default.Person,
            placeholder = "Masukkan nama dokter",
            enabled = enabled
        )

        // Kontak
        InputFieldWithIcon(
            label = "Kontak",
            value = insertDktrUiEvent.kontak,
            onValueChange = { onValueChange(insertDktrUiEvent.copy(kontak = it)) },
            icon = Icons.Default.Phone,
            placeholder = "Masukkan nomor kontak",
            enabled = enabled
        )

        // Spesialisasi
        InputFieldWithIcon(
            label = "Spesialisasi",
            value = insertDktrUiEvent.spesialisasi,
            onValueChange = { onValueChange(insertDktrUiEvent.copy(spesialisasi = it)) },
            icon = Icons.Default.ThumbUp,
            placeholder = "Masukkan bidang spesialisasi",
            enabled = enabled
        )

        if (enabled) {
            Text(
                text = "Harap isi semua data dengan benar!",
                modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

        Divider(
            thickness = 8.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Composable
fun InputFieldWithIcon(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = { Text(text = placeholder) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}