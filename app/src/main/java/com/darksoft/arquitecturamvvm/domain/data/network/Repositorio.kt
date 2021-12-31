package com.darksoft.arquitecturamvvm.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darksoft.arquitecturamvvm.VistaClinicaDatos
import com.google.firebase.firestore.FirebaseFirestore

class Repositorio {

    fun getClinicaData(): LiveData<MutableList<VistaClinicaDatos>>{
        val mutableData = MutableLiveData<MutableList<VistaClinicaDatos>>()

        FirebaseFirestore.getInstance().collection("DatosClinicas").get().addOnSuccessListener { result ->
            val listData = mutableListOf<VistaClinicaDatos>()
            for (document in result){
                val image_url = document.getString("image_url")
                val nombre_clinica = document.getString("nombre_clinica")

                val dataClinica = VistaClinicaDatos(image_url!!, nombre_clinica!!)
                listData.add(dataClinica)
            }

            mutableData.value = listData
        }
        return mutableData
    }

}