package com.caspex.walmart.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.caspex.walmart.data.Country
import com.caspex.walmart.domain.usecase.GetCountryUsecase
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val usecase = GetCountryUsecase()

    private val _countries = MutableLiveData(Result.success(Country()))
    val countries: MutableLiveData<Result<Country>> get() = _countries

    fun fetchCountries() {
        viewModelScope.launch {
            usecase().collect { result ->
                _countries.value = result
            }
        }
    }
}

class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel() as T
    }
}