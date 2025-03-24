package com.caspex.walmart.domain.usecase

import com.caspex.walmart.data.Country
import com.caspex.walmart.data.repository.CountryRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryUsecase {
    private val repository = CountryRepositoryImpl()
    operator fun invoke(): Flow<Result<Country>> = flow {
        try {
            val response = repository.getCountries()
            if (response.isSuccessful) {
                response.body()?.let { emit(Result.success(it)) }
            } else {
                emit(Result.failure(Exception("API Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}