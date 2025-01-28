package com.example.klinikhewan.service_api

import com.example.klinikhewan.model.AllPerawatanResponse
import com.example.klinikhewan.model.Perawatan
import com.example.klinikhewan.model.PerawatanDetailResponse
import retrofit2.Response
import retrofit2.http.*

interface PerawatanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    // Insert a new Perawatan (Treatment)
    @POST("perawatan/store")
    suspend fun insertPerawatan(@Body perawatan: Perawatan)

    // Get All Perawatan (Treatments)
    @GET("perawatan/")
    suspend fun getAllPerawatan(): AllPerawatanResponse

    // Get Perawatan by ID (Treatment Detail)
    @GET("perawatan/{id_perawatan}")
    suspend fun getPerawatanById(@Path("id_perawatan") id_perawatan: Int): PerawatanDetailResponse

    // Update Perawatan Data (Treatment)
    @PUT("perawatan/{id_perawatan}")
    suspend fun updatePerawatan(@Path("id_perawatan") id_perawatan: Int, @Body perawatan: Perawatan)

    // Delete Perawatan (Treatment)
    @DELETE("perawatan/{id_perawatan}")
    suspend fun deletePerawatan(@Path("id_perawatan") id_perawatan: Int): Response<Void>
}

