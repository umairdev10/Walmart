package com.caspex.walmart.domain.repository

import com.caspex.walmart.data.Country
import retrofit2.Response

interface CountryRepository {
    suspend fun getCountries(): Response<Country>
}