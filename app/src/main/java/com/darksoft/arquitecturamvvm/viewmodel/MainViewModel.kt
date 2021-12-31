package com.darksoft.arquitecturamvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darksoft.arquitecturamvvm.VistaClinicaDatos
import com.darksoft.arquitecturamvvm.domain.data.network.Repositorio

class MainViewModel: ViewModel() {

    private val repo = Repositorio()

    fun fetchClinicaData(): LiveData<MutableList<VistaClinicaDatos>>{
        val mutableData = MutableLiveData<MutableList<VistaClinicaDatos>>()

        repo.getClinicaData().observeForever{listClinica ->
            mutableData.value = listClinica
        }
        return mutableData
    }

}