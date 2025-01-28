package com.example.klinikhewan.repository

import com.example.klinikhewan.service_api.PerawatanService
import com.example.klinikhewan.model.AllPerawatanResponse
import com.example.klinikhewan.model.Perawatan
import com.example.klinikhewan.model.PerawatanDetailResponse
import java.io.IOException

interface PerawatanRepository {

    suspend fun insertPerawatan(perawatan: Perawatan)

    suspend fun getAllPerawatan(): AllPerawatanResponse

    suspend fun updatePerawatan(idPerawatan: Int, perawatan: Perawatan)

    suspend fun deletePerawatan(idPerawatan: Int)

    suspend fun getPerawatanById(idPerawatan: Int): Perawatan
}

class NetworkPerawatanRepository(
    private val perawatanApiService: PerawatanService
) : PerawatanRepository {

    override suspend fun insertPerawatan(perawatan: Perawatan) {
        perawatanApiService.insertPerawatan(perawatan)
    }

    override suspend fun getAllPerawatan(): AllPerawatanResponse =
        perawatanApiService.getAllPerawatan()

    override suspend fun updatePerawatan(idPerawatan: Int, perawatan: Perawatan) {
        perawatanApiService.updatePerawatan(idPerawatan, perawatan)
    }

    override suspend fun deletePerawatan(idPerawatan: Int) {
        try {
            val response = perawatanApiService.deletePerawatan(idPerawatan)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete perawatan. HTTP Status code:" +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPerawatanById(idPerawatan: Int): Perawatan =
        perawatanApiService.getPerawatanById(idPerawatan).data
}
