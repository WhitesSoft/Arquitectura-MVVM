package com.darksoft.arquitecturamvvm.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darksoft.arquitecturamvvm.model.HomeRecyclerViewItem
import com.google.firebase.firestore.FirebaseFirestore

class Repositorio {

    fun getClinics(): LiveData<MutableList<HomeRecyclerViewItem>>{

        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        FirebaseFirestore.getInstance().collection("OptionsClinicas").document("clinicas")
            .collection("clinicas").get().addOnSuccessListener { result ->
            val listData = mutableListOf<HomeRecyclerViewItem>()
            //listData.add(HomeRecyclerViewItem.Title("1", "Puede ser shi?"))
            for (document in result){
                val id = document.getString("id")
                val image_url = document.getString("image_url")
                val nombre_clinica = document.getString("nombre_clinica")

                val dataClinica = HomeRecyclerViewItem.Clinics(id!!, image_url!!, nombre_clinica!!)
                listData.add(dataClinica)
            }

            mutableData.value = listData
        }


        return mutableData
    }

    fun getOdonto(): LiveData<MutableList<HomeRecyclerViewItem>>{

        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        FirebaseFirestore.getInstance().collection("OptionsClinicas").document("odonto")
            .collection("odonto").get().addOnSuccessListener { result ->
                val listData = mutableListOf<HomeRecyclerViewItem>()
                //listData.add(HomeRecyclerViewItem.Title("1", "Puede ser shi?"))
                for (document in result){
                    val id = document.getString("id")
                    val image_url = document.getString("image_url")
                    val nombre_clinica = document.getString("nombre_clinica")

                    val dataClinica = HomeRecyclerViewItem.Clinics(id!!, image_url!!, nombre_clinica!!)
                    listData.add(dataClinica)
                }

                mutableData.value = listData
            }


        return mutableData
    }

    fun getConsultorios(): LiveData<MutableList<HomeRecyclerViewItem>>{

        val mutableData = MutableLiveData<MutableList<HomeRecyclerViewItem>>()

        FirebaseFirestore.getInstance().collection("OptionsClinicas").document("consultorios")
            .collection("consultorios").get().addOnSuccessListener { result ->
                val listData = mutableListOf<HomeRecyclerViewItem>()

                for (document in result){
                    val id = document.getString("id")
                    val image_url = document.getString("image_url")
                    val nombre_clinica = document.getString("nombre_clinica")

                    val dataClinica = HomeRecyclerViewItem.Clinics(id!!, image_url!!, nombre_clinica!!)
                    listData.add(dataClinica)
                }

                mutableData.value = listData
            }


        return mutableData
    }

}