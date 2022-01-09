package com.darksoft.arquitecturamvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darksoft.arquitecturamvvm.domain.data.network.Repositorio
import com.darksoft.arquitecturamvvm.model.HomeRecyclerViewItem

class MainViewModel: ViewModel() {

    private val repo = Repositorio()

    fun fetchClinics():LiveData<MutableList<HomeRecyclerViewItem>>{
        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        repo.getClinics().observeForever{data ->
            mutableData.value = data
        }

        return mutableData
    }

    fun fetchOdonto():LiveData<MutableList<HomeRecyclerViewItem>>{
        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        repo.getOdonto().observeForever{data ->
            mutableData.value = data
        }

        return mutableData
    }


    fun fetchConsultorios():LiveData<MutableList<HomeRecyclerViewItem>>{
        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        repo.getConsultorios().observeForever{data ->
            mutableData.value = data
        }

        return mutableData
    }

}