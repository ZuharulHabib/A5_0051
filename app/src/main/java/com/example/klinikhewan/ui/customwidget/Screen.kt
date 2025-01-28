package com.example.klinikhewan.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.klinikhewan.R
import com.example.klinikhewan.ui.PenyediaViewModel
import com.example.klinikhewan.ui.pages.dokter.viewmodel.HomeDokterViewModel

@Composable
fun Screen1(
    viewModel: HomeDokterViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onNavigate: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoanimal),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
                .height(700.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dokter),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )

            Button(
                onClick = {
                    onNavigate()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(text = "Hewan Sehat dan Kuat")
            }
        }
    }
}
