package com.caspex.walmart.data.repository

import com.caspex.walmart.data.Country
import com.caspex.walmart.data.remote.RetrofitClient
import com.caspex.walmart.domain.repository.CountryRepository
import retrofit2.Response

class CountryRepositoryImpl : CountryRepository {
    private val apiInterface = RetrofitClient.apiService
    override suspend fun getCountries(): Response<Country> {
        return apiInterface.getCountries()
    }
}