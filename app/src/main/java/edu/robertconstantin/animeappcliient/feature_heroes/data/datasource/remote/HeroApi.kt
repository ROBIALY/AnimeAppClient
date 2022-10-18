package edu.robertconstantin.animeappcliient.feature_heroes.data.datasource.remote

import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import retrofit2.Response
import retrofit2.http.GET

interface HeroApi {
    @GET("api/hero/all")
    suspend fun getAllHeroes(): Response<List<HeroDto>>


    companion object {
        const val HERO_BASE_URL = "http://10.0.2.2:8001/"
    }
}