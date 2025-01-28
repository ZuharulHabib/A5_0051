package com.klinikhewan.ui.navigation

import com.example.klinikhewan.ui.navigation.AddPasien
import com.example.klinikhewan.ui.navigation.DesttinasiDetailPasien
import com.example.klinikhewan.ui.navigation.Home
import com.example.klinikhewan.ui.navigation.HomeDokter
import com.example.klinikhewan.ui.navigation.HomeJenis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.klinikhewan.ui.navigation.AddDokter
import com.example.klinikhewan.ui.navigation.AddJenis
import com.example.klinikhewan.ui.navigation.DestinasiDetailDokter
import com.example.klinikhewan.ui.navigation.DestinasiDetailJenis
import com.example.klinikhewan.ui.navigation.UpdateJenis
import com.example.klinikhewan.ui.navigation.UpdatePasien
import com.example.klinikhewan.ui.pages.dokter.view.DetailDokterScreen
import com.example.klinikhewan.ui.pages.dokter.view.EntryDokterScreen
import com.example.klinikhewan.ui.pages.dokter.view.HomeDokterScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.DetailJenisScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.EntryJnsScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.HomeJenisScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.UpdateJenisScreen
import com.example.klinikhewan.ui.pages.pasien.view.DestinasiDetailPasien
import com.example.klinikhewan.ui.pages.pasien.view.DetailScreenPasien
import com.example.klinikhewan.ui.pages.pasien.view.EntryPasienScreen
import com.example.klinikhewan.ui.pages.pasien.view.HomeScreen
import com.example.klinikhewan.ui.pages.pasien.view.UpdatePasienScreen


@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = Modifier,
    ){
        composable(Home.route){
            HomeScreen(
                navigateToEntry = { navController.navigate(AddPasien.route) },
                navigateToSeeDokter = { navController.navigate(HomeDokter.route) },
                navigateToSeeJenis = { navController.navigate(HomeJenis.route) },
                onDetailClick = { idhewan ->
                    navController.navigate("${DestinasiDetailPasien.route}/$idhewan")
                }
            )
        }
        composable(AddPasien.route){
            EntryPasienScreen(
                navigateBack = { navController.navigate(Home.route) {
                    popUpTo(Home.route) {
                        inclusive = true
                    }
                }
                }
            )
        }
        composable(DesttinasiDetailPasien.routeWithArg, arguments = listOf(navArgument(DesttinasiDetailPasien.idPASIEN) {
            type = NavType.StringType }
        )
        ){
            val idhewan = it.arguments?.getString(DesttinasiDetailPasien.idPASIEN)
            idhewan?.let { hewan ->
                DetailScreenPasien(
                    navigateToItemUpdatePsn = { navController.navigate("${UpdatePasien.route}/$idhewan") },
                    navigateBack = { navController.navigate(Home.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(UpdatePasien.routeWithArg, arguments = listOf(navArgument(DesttinasiDetailPasien.idPASIEN){
            type = NavType.StringType
        }
        )
        ){
            val idhewan = it.arguments?.getString(UpdatePasien.idPASIEN)
            idhewan?.let { hewan ->
                UpdatePasienScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(HomeJenis.route){
            HomeJenisScreen(
                navigateToAddEntry = { navController.navigate(AddJenis.route) {
                    popUpTo(HomeJenis.route) {
                        inclusive = true
                    }
                }
                },
                onDetailClickJenis = { idjenishewan ->
                    navController.navigate("${DestinasiDetailJenis.route}/$idjenishewan")
                }
            )
        }
        composable(DestinasiDetailJenis.routeWithArg, arguments = listOf(navArgument(DestinasiDetailJenis.idJENIS) {
            type = NavType.StringType }
        )
        ){
            val idjenishewan = it.arguments?.getString(DestinasiDetailJenis.idJENIS)
            idjenishewan?.let { jenis ->
                DetailJenisScreen(
                    navigateToItemUpdateJns = { navController.navigate("${UpdateJenis.route}/$idjenishewan") },
                    navigateBack = { navController.navigate(HomeJenis.route) {
                        popUpTo(HomeJenis.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(UpdateJenis.routeWithArg, arguments = listOf(navArgument(DestinasiDetailJenis.idJENIS){
            type = NavType.StringType
        }
        )
        ){
            val idjenishewan = it.arguments?.getString(UpdateJenis.idJENIS)
            idjenishewan?.let { jenis ->
                UpdateJenisScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(AddJenis.route){
            EntryJnsScreen(navigateBack = {
                navController.navigate(HomeJenis.route) {
                    popUpTo(HomeJenis.route) {
                        inclusive = true
                    }
                }
            }
            )
        }
        composable(HomeDokter.route) {
            HomeDokterScreen(
                navigateToAddEntry = { navController.navigate(AddDokter.route)},
            )
        }

        composable(AddDokter.route){
            EntryDokterScreen(navigateBack = {
                navController.navigate(HomeDokter.route) {
                    popUpTo(HomeDokter.route) {
                        inclusive = true
                    }
                }
            })
        }
    }

}

