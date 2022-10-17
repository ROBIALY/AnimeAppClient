package edu.robertconstantin.animeappcliient.feature_heroes.data.remote

import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import retrofit2.Response
import retrofit2.http.GET

interface HeroApi {
    @GET("api/hero/all")
    suspend fun getAllHeroes(): Response<List<HeroDto>>
}