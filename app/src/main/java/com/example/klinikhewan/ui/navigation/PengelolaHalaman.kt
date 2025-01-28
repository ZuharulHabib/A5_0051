package com.klinikhewan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.klinikhewan.ui.customwidget.Screen1
import com.example.klinikhewan.ui.navigation.AddDokter
import com.example.klinikhewan.ui.navigation.AddJenis
import com.example.klinikhewan.ui.navigation.AddPasien
import com.example.klinikhewan.ui.navigation.AddPerawatan
import com.example.klinikhewan.ui.navigation.DestinasiDetailDokter
import com.example.klinikhewan.ui.navigation.DestinasiDetailJenis
import com.example.klinikhewan.ui.navigation.DestinasiDetailPerawatan
import com.example.klinikhewan.ui.navigation.DesttinasiDetailPasien
import com.example.klinikhewan.ui.navigation.Home
import com.example.klinikhewan.ui.navigation.HomeDokter
import com.example.klinikhewan.ui.navigation.HomeJenis
import com.example.klinikhewan.ui.navigation.HomePerawatan
import com.example.klinikhewan.ui.navigation.Screen
import com.example.klinikhewan.ui.navigation.UpdateDokter
import com.example.klinikhewan.ui.navigation.UpdateJenis
import com.example.klinikhewan.ui.navigation.UpdatePasien
import com.example.klinikhewan.ui.navigation.UpdatePerawatan
import com.example.klinikhewan.ui.pages.dokter.view.DetailDokterScreen
import com.example.klinikhewan.ui.pages.dokter.view.EntryDokterScreen
import com.example.klinikhewan.ui.pages.dokter.view.HomeDokterScreen
import com.example.klinikhewan.ui.pages.dokter.view.UpdateDokterScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.DetailJenisScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.EntryJnsScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.HomeJenisScreen
import com.example.klinikhewan.ui.pages.jenishewan.view.UpdateJenisScreen
import com.example.klinikhewan.ui.pages.pasien.view.DetailScreenPasien
import com.example.klinikhewan.ui.pages.pasien.view.EntryPasienScreen
import com.example.klinikhewan.ui.pages.pasien.view.HomeScreen
import com.example.klinikhewan.ui.pages.pasien.view.UpdatePasienScreen
import com.example.klinikhewan.ui.pages.perawatan.view.DetailPerawatanScreen
import com.example.klinikhewan.ui.pages.perawatan.view.HomePerawatnScreen
import com.example.klinikhewan.ui.pages.perawatan.view.InsertPerawatanScreen
import com.example.klinikhewan.ui.pages.perawatan.view.UpdatePerawatanScreen


@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.route,
        modifier = Modifier,
    ){

        composable(Screen.route){
            Screen1(onNavigate = {
                navController.navigate(Home.route) {
                    popUpTo(Screen.route) {
                        inclusive = true
                    }
                }
            }
            )
        }
        composable(Home.route){
            HomeScreen(
                navigateToEntry = { navController.navigate(AddPasien.route) },
                navigateToSeeDokter = { navController.navigate(HomeDokter.route) },
                navigateToSeeJenis = { navController.navigate(HomeJenis.route) },
                navigateToSeePerawatan = { navController.navigate(HomePerawatan.route) },
                onDetailClick = { idhewan ->
                    navController.navigate("${DesttinasiDetailPasien.route}/$idhewan")
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
                    navigateToAddPerawatan = { navController.navigate(AddPerawatan.route) {
                        popUpTo(HomePerawatan.route) {
                            inclusive = true
                        }
                    }
                    },
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
                navigateToAdd = { navController.navigate(AddJenis.route) {
                    popUpTo(HomeJenis.route) {
                        inclusive = true
                    }
                }
                },
                onDetailClickJenis = { idjenishewan ->
                    navController.navigate("${DestinasiDetailJenis.route}/$idjenishewan")
                },
                navigateToBack = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
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
        composable(HomeDokter.route){
            HomeDokterScreen(
                navigateToAdd = { navController.navigate(AddDokter.route) {
                    popUpTo(HomeDokter.route) {
                        inclusive = true
                    }
                }
                },
                onDetailClickDokter = { iddokter ->
                    navController.navigate("${DestinasiDetailDokter.route}/$iddokter")
                },
                navigateToBack = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                }
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
        composable(DestinasiDetailDokter.routeWithArg, arguments = listOf(navArgument(DestinasiDetailDokter.idDOKTER) {
            type = NavType.StringType }
        )
        ){
            val iddokter = it.arguments?.getString(DestinasiDetailDokter.idDOKTER)
            iddokter?.let { dokter ->
                DetailDokterScreen(
                    navigateToItemUpdateDktr = { navController.navigate("${UpdateDokter.route}/$iddokter") },
                    navigateBack = { navController.navigate(HomeDokter.route) {
                        popUpTo(HomeDokter.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(UpdateDokter.routeWithArg, arguments = listOf(navArgument(DestinasiDetailDokter.idDOKTER){
            type = NavType.StringType
        }
        )
        ){
            val iddokter = it.arguments?.getString(UpdateDokter.idDOKTER)
            iddokter?.let { dokter ->
                UpdateDokterScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
        composable(HomePerawatan.route){
            HomePerawatnScreen(
                onDetailClickPerawatan = { idperawatan ->
                    navController.navigate("${DestinasiDetailPerawatan.route}/$idperawatan")
                },
                navigateToBack = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(AddPerawatan.route){
            InsertPerawatanScreen(
                navigateBack = { navController.navigate(HomePerawatan.route) {
                    popUpTo(HomePerawatan.route) {
                        inclusive = true
                    }
                }
                }
            )
        }
        composable(DestinasiDetailPerawatan.routeWithArg, arguments = listOf(navArgument(DestinasiDetailPerawatan.idPERAWATAN) {
            type = NavType.StringType }
        )
        ){
            val idperawatan = it.arguments?.getString(DestinasiDetailPerawatan.idPERAWATAN)
            idperawatan?.let { perawatan ->
                DetailPerawatanScreen(
                    navigateToItemUpdatePrwtn = { navController.navigate("${UpdatePerawatan.route}/$idperawatan") },
                    navigateBack = { navController.navigate(HomePerawatan.route) {
                        popUpTo(HomePerawatan.route) { inclusive = true }
                    }
                    }
                )
            }
        }
        composable(UpdatePerawatan.routeWithArg, arguments = listOf(navArgument(DestinasiDetailPerawatan.idPERAWATAN){
            type = NavType.StringType
        }
        )
        ){
            val idperawatan = it.arguments?.getString(UpdatePerawatan.idPERAWATAN)
            idperawatan?.let { perawatan ->
                UpdatePerawatanScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}
