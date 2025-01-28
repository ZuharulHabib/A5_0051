package com.example.klinikhewan.di

import com.example.klinikhewan.repository.DokterRepository
import com.example.klinikhewan.repository.JenisRepository
import com.example.klinikhewan.repository.NetworkDokterRepository
import com.example.klinikhewan.repository.NetworkJenisRepository
import com.example.klinikhewan.repository.NetworkKontakRepository
import com.example.klinikhewan.repository.NetworkPerawatanRepository
import com.example.klinikhewan.repository.PasienRepository
import com.example.klinikhewan.repository.PerawatanRepository
import com.example.klinikhewan.service_api.DokterService
import com.example.klinikhewan.service_api.JenisService
import com.example.klinikhewan.service_api.PasienService
import com.example.klinikhewan.service_api.PerawatanService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val pasienRepository: PasienRepository
    val jenisRepository: JenisRepository
    val dokterRepository: DokterRepository
    val perawatanRepository: PerawatanRepository
}

class KlinikHewanContainer : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/api/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pasienService: PasienService by lazy {
        retrofit.create(PasienService::class.java)
    }

    private val jenisService: JenisService by lazy {
        retrofit.create(JenisService::class.java)
    }

    private val dokterService: DokterService by lazy {
        retrofit.create(DokterService::class.java)
    }

    private val perawatanService: PerawatanService by lazy {
        retrofit.create(PerawatanService::class.java)
    }

    override val pasienRepository: PasienRepository by lazy {
        NetworkKontakRepository(pasienService)
    }
    override val jenisRepository: JenisRepository by lazy {
        NetworkJenisRepository(jenisService)
    }
    override val dokterRepository: DokterRepository by lazy {
        NetworkDokterRepository(dokterService)
    }
    override val perawatanRepository: PerawatanRepository by lazy {
        NetworkPerawatanRepository(perawatanService)
    }
}