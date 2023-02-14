package com.rafaelleal.android.presentation_address.address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelleal.android.domain.usecase.GetAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddressViewModel @Inject constructor(
    private val useCase: GetAddressUseCase,
    private val converter: AddressConverter
) : ViewModel(){

    val TAG = "Main"

   private val _addressFlow = MutableStateFlow<UiState<AddressModel>>(UiState.Waiting)

    val addressFlow : StateFlow<UiState<AddressModel>> = _addressFlow

    fun fetchAddress(cep: String) {

        _addressFlow.value = UiState.Loading

        Log.i(TAG, "AddressViewModel.fetchAddress" )
        viewModelScope.launch {
            useCase.execute(GetAddressUseCase.Request(cep))
                .map {
                    converter.convert(it)
                }
                .collect{
                    _addressFlow.value = it
                }
        }
    }

}