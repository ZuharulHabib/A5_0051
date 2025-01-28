package com.example.klinikhewan.ui.pages.pasien.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.customwidget.CostumeTopAppBar
import com.example.klinikhewan.ui.navigation.AddPasien
import com.example.klinikhewan.ui.pages.pasien.viewmodel.InsertPsnUiEvent
import com.example.klinikhewan.ui.pages.pasien.viewmodel.InsertPsnUiState
import com.example.klinikhewan.ui.pages.pasien.viewmodel.InsertPsnViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPasienScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPsnViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = AddPasien.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                EntryBody(
                    insertPsnUiState = viewModel.uiStatePsn,
                    onPasienValueChange = viewModel::updateInsertPsnState,
                    onSaveClick = {
                        coroutineScope.launch {
                            viewModel.insertPsn()
                            navigateBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EntryBody(
    insertPsnUiState: InsertPsnUiState,
    onPasienValueChange: (InsertPsnUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
    ) {
        FormInput(
            insertPsnUiEvent = insertPsnUiState.insertPsnUiEvent,
            onValueChange = onPasienValueChange,
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
    insertPsnUiEvent: InsertPsnUiEvent,
    onValueChange: (InsertPsnUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputFieldWithIcon(
            label = "Id Hewan",
            value = insertPsnUiEvent.idhewan,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(idhewan = it)) },
            icon = Icons.Default.Info,
            placeholder = "Masukkan ID hewan"
        )
        InputFieldWithIcon(
            label = "Nama Hewan",
            value = insertPsnUiEvent.namahewan,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(namahewan = it)) },
            icon = Icons.Default.AccountBox,
            placeholder = "Masukkan nama hewan"
        )
        InputFieldWithIcon(
            label = "Jenis Id",
            value = insertPsnUiEvent.jenishewanid,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(jenishewanid = it)) },
            icon = Icons.Default.Info,
            placeholder = "Masukkan jenis ID"
        )
        InputFieldWithIcon(
            label = "Pemilik",
            value = insertPsnUiEvent.pemilik,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(pemilik = it)) },
            icon = Icons.Default.Person,
            placeholder = "Masukkan nama pemilik"
        )
        InputFieldWithIcon(
            label = "Kontak Terhubung",
            value = insertPsnUiEvent.kontakpemilik,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(kontakpemilik = it)) },
            icon = Icons.Default.Phone,
            placeholder = "Masukkan nomor kontak pemilik"
        )
        InputFieldWithIcon(
            label = "Tanggal Lahir",
            value = insertPsnUiEvent.tanggallahir,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(tanggallahir = it)) },
            icon = Icons.Default.DateRange,
            placeholder = "Masukkan tanggal lahir (yyyy-mm-dd)"
        )
        InputFieldWithIcon(
            label = "Catatan Kesehatan",
            value = insertPsnUiEvent.catatankesehatan,
            onValueChange = { onValueChange(insertPsnUiEvent.copy(catatankesehatan = it)) },
            icon = Icons.Default.Email,
            placeholder = "Masukkan catatan kesehatan"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFieldWithIcon(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
