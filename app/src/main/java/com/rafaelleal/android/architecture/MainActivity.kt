package com.rafaelleal.android.architecture

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.rafaelleal.android.architecture.databinding.ActivityMainBinding
import com.rafaelleal.android.presentation_address.address.AddressModel
import com.rafaelleal.android.presentation_address.address.AddressViewModel
import com.rafaelleal.android.presentation_address.address.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = "Main"

    val viewModel: AddressViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViews()
    }

    fun setupViews() {
        setupCollectors()
        setupClickListeners()
    }

    fun setupClickListeners(){
        binding.btnGetCep.setOnClickListener {
            Log.i(TAG, "Clique no botão" )
            viewModel.fetchAddress(
                binding.inputCep.text.toString()
            )
        }
    }

    fun setupCollectors(){

        lifecycleScope.launchWhenStarted {

            launch {
                viewModel.addressFlow.collect { state ->
                    Log.i(TAG, "Collect: ${state}")
                    when (state) {
                        is UiState.Waiting -> {
                            setWaiting()
                        }
                        is UiState.Loading -> {
                            showLoading(true)
                        }
                        is UiState.Error -> {
                            //Error(state.errorMessage)
                            displayError(state.errorMessage)
                        }
                        is UiState.Success -> {
                            displayAddress(state.data)
                        }
                    }
                }
            }
        }
    }

    fun setWaiting() {
        binding.tvAddessResult.setText("Esperando Busca")
    }

    fun showLoading(value : Boolean) {
        binding.pbMain.visibility = if (value) View.VISIBLE else View.GONE
    }

    fun displayAddress(addressModel: AddressModel) {
        showLoading(false)
        binding.tvAddessResult.setText(
            getTextFromAddressModel(addressModel)
        )
        binding.tvAddessResult.setTextColor(Color.BLUE)
    }

    fun displayError(msg: String) {
        showLoading(false)
        binding.tvAddessResult.setText(msg)
        binding.tvAddessResult.setTextColor(Color.RED)
    }

    fun getTextFromAddressModel(addressModel: AddressModel) : String {
        var text = ""
        text = "$text${addressModel.cep}\n"
        text = "$text${addressModel.estado}\n"
        text = "$text${addressModel.cidade}\n"
        text = "$text${addressModel.bairro}\n"
        text = "$text${addressModel.rua}\n"

        return text

    }



}