package com.darksoft.arquitecturamvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.darksoft.arquitecturamvvm.R
import com.darksoft.arquitecturamvvm.databinding.ActivityInformacionClinicaBinding
import com.darksoft.arquitecturamvvm.model.HomeRecyclerViewItem
import com.google.firebase.firestore.FirebaseFirestore

class InformacionClinica : AppCompatActivity() {

    private lateinit var binding: ActivityInformacionClinicaBinding

    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionClinicaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (intent.extras != null){
             name = intent.getStringExtra("id").toString()
        }



        FirebaseFirestore.getInstance().collection("OptionsClinicas").document("clinicas")
            .collection("clinicas").document(name).get().addOnSuccessListener {

                //Toast.makeText(this, id, Toast.LENGTH_LONG).show()
                binding.dara.text = it.getString("id")
                Glide.with(this).load(it.getString("image_url")).into(binding.foto)
                binding.title.text = it.getString("nombre_clinica")
            }

        //binding.dara.text = id
    }

    override fun onBackPressed() {
        super.onBackPressed()
        name = ""
    }
}