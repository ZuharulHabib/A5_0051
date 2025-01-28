package com.example.klinikhewan.repository

import com.example.klinikhewan.model.AllPerawatanResponse
import com.example.klinikhewan.model.Perawatan
import com.example.klinikhewan.service_api.PerawatanService
import java.io.IOException

interface PerawatanRepository{
    suspend fun insertPerawatan(perawatan: Perawatan)

    suspend fun getAllPerawatan(): AllPerawatanResponse

    suspend fun updatePerawatan(idperawatan: String, perawatan: Perawatan)

    suspend fun deletePerawatan(idperawatan: String)

    suspend fun getPerawatanById(idperawatan: String): Perawatan
}

class NetworkPerawatanRepository(
    private val perawatanApiService: PerawatanService
) : PerawatanRepository {
    override suspend fun insertPerawatan(perawatan: Perawatan) {
        perawatanApiService.insertPerawatan(perawatan)
    }
    override suspend fun getAllPerawatan(): AllPerawatanResponse {
        return perawatanApiService.getAllPerawatan()
    }
    override suspend fun getPerawatanById(idperawatan: String): Perawatan {
        return perawatanApiService.getPerawatanById(idperawatan).data
    }
    override suspend fun updatePerawatan(idperawatan: String, perawatan: Perawatan) {
        perawatanApiService.updatePerawatan(idperawatan, perawatan)
    }
    override suspend fun deletePerawatan(idperawatan: String) {
        try {
            val response = perawatanApiService.deletePerawatan(idperawatan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete perawatan. HTTP Status Code:"+
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