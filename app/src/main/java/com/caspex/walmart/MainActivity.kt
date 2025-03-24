package com.caspex.walmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.caspex.walmart.databinding.MainLayoutBinding
import com.caspex.walmart.presentation.CountryAdapter
import com.caspex.walmart.presentation.CountryViewModel
import com.caspex.walmart.presentation.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainLayoutBinding
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBinding()
        observerResponse()
    }

    private fun initBinding() {
        viewModel =
            ViewModelProvider(this, MainViewModelFactory())[CountryViewModel::class.java]

        adapter = CountryAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(binding.recyclerView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun observerResponse() {
            viewModel.countries.observe(this) { result ->
                result.fold(
                    onSuccess = { adapter.updateData(it) },
                    onFailure = {}
                )
            }
        viewModel.fetchCountries()
    }
}