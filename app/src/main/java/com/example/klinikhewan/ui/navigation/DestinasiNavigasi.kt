package com.example.klinikhewan.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object Home : DestinasiNavigasi {
    override val route = "Daftar Pasien"
    override val titleRes = "Data Pasien"
}

object HomeJenis : DestinasiNavigasi {
    override val route = "home_jenis"
    override val titleRes = "Home Data Jenis"
}

object HomeDokter : DestinasiNavigasi {
    override val route = "home_dokter"
    override val titleRes = "Home Data Dokter"
}

object AddPasien : DestinasiNavigasi {
    override val route = "Tambah Pasien"
    override val titleRes = "Entry Data Pasien"
}

object AddJenis : DestinasiNavigasi {
    override val route = "Tambah Jenis"
    override val titleRes = "Entry Data Jenis"
}

object AddDokter : DestinasiNavigasi {
    override val route = "Tambah Dokter"
    override val titleRes = "Entry Data Dokter"
}

object UpdatePasien: DestinasiNavigasi {
    override val route = "update pasien"
    override val titleRes = "Update Pasien"
    const val idPASIEN = "idhewan"
    val routeWithArg = "$route/{$idPASIEN}"
}
object UpdateDokter: DestinasiNavigasi {
    override val route = "update dokter"
    override val titleRes = "Update Dokter"
    const val idDOKTER = "idDokter"
    val routeWithArg = "$route/{$idDOKTER}"
}

object DesttinasiDetailPasien: DestinasiNavigasi {
    override val route = "detail pasien"
    override val titleRes = "Detail Pasien"
    const val idPASIEN = "idhewan"
    val routeWithArg = "$route/{$idPASIEN}"
}

object DestinasiDetailDokter : DestinasiNavigasi {
    override val route = "detail dokter"
    override val titleRes = "Detail dokter"
    const val idDOKTER = "id_dokter"
    val routeWithArg = "$route/{$idDOKTER}"
}


object DestinasiDetailJenis : DestinasiNavigasi {
    override val route = "detail jenis"
    override val titleRes = "Detail Jenis"
    const val idJENIS = "idjenis"
    val routeWithArg = "$route/{$idJENIS}"
}

object UpdateJenis: DestinasiNavigasi {
    override val route = "update jenis"
    override val titleRes = "Update Jenis"
    const val idJENIS = "idjenis"
    val routeWithArg = "$route/{$idJENIS}"
}


