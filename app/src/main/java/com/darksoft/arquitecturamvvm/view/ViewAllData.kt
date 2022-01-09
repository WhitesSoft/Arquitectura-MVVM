package com.darksoft.arquitecturamvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darksoft.arquitecturamvvm.R
import com.darksoft.arquitecturamvvm.databinding.ActivityViewAllDataBinding
import com.darksoft.arquitecturamvvm.model.HomeRecyclerAdapter
import com.darksoft.arquitecturamvvm.viewmodel.MainViewModel

class ViewAllData : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllDataBinding
    private val allData = HomeRecyclerAdapter()
    private val viewModel by lazy{ ViewModelProvider(this)[MainViewModel::class.java] }

    private lateinit var clinics: String
    private lateinit var odonto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.tool))

        if(intent.extras != null){
            clinics = intent.getStringExtra("clinicas").toString()
            odonto = intent.getStringExtra("odonto").toString()
        }

        binding.tool.back.setOnClickListener {
            finish()
        }

        setupRecyclerView()



    }

    private fun setupRecyclerView(){
       // adaptador = HomeRecyclerAdapter(this, this)

        binding.rv1.apply {
            layoutManager = LinearLayoutManager(this@ViewAllData, LinearLayoutManager.VERTICAL, false)
            adapter = allData

            if (clinics.equals("1")){
                binding.tool.titulo.text = "CLÍNICAS"
                binding.shimmerViewContainer.startShimmer()

                viewModel.fetchClinics().observeForever{
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                    allData.items = it
                }
            }

            if (odonto.equals("2")){
                binding.tool.titulo.text = "CLÍNICAS ODONTOLOGICAS"
                binding.shimmerViewContainer.startShimmer()

                viewModel.fetchOdonto().observeForever{
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                    allData.items = it
                }
            }

        }
//        binding.rv1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.rv1.adapter = adaptador
//
//        if (options.equals("1")){
//            viewModel.fetchClinics().observe(this){
//                adaptador.items = it
//            }
//        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        clinics = ""
    }



}