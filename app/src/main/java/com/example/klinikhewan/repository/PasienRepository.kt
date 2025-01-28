package com.example.klinikhewan.repository

import com.example.klinikhewan.model.AllPasienResponse
import com.example.klinikhewan.model.Pasien
import com.example.klinikhewan.service_api.PasienService
import java.io.IOException

interface PasienRepository{
    suspend fun insertPasien(pasien: Pasien)

    suspend fun getAllPasien(): AllPasienResponse

    suspend fun getPasienById(idhewan: String): Pasien

    suspend fun updatePasien(idhewan: String, pasien: Pasien)

    suspend fun deletePasien(idhewan: String)
}

class NetworkKontakRepository(
    private val pasienApiService: PasienService
) : PasienRepository {
    override suspend fun insertPasien(pasien: Pasien) {
        pasienApiService.insertPasien(pasien)
    }
    override suspend fun updatePasien(idhewan: String, pasien: Pasien) {
        pasienApiService.updatePasien(idhewan, pasien)
    }
    override suspend fun getAllPasien(): AllPasienResponse {
        return pasienApiService.getAllPasien()
    }
    override suspend fun getPasienById(idhewan: String): Pasien {
        return pasienApiService.getPasienById(idhewan).data
    }
    override suspend fun deletePasien(idhewan: String) {
        try {
            val response = pasienApiService.deletePasien(idhewan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pasien. HTTP Status Code:"+
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
