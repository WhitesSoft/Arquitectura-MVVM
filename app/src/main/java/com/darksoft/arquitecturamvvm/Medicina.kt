package com.darksoft.arquitecturamvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darksoft.arquitecturamvvm.databinding.ActivityMedicinaBinding
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding
import com.darksoft.arquitecturamvvm.viewmodel.MainViewModel

class Medicina : AppCompatActivity(), MainAdapter.onItemClickListener {

    private lateinit var binding: ActivityMedicinaBinding
    private lateinit var adaptador: MainAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(MainViewModel::class.java) }

    private lateinit var options: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicinaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.tool))

        if(intent.extras != null){
            options = intent.getStringExtra("id").toString()
        }

        setupRecyclerView()

        if (options.equals("1"))
            observeDataMedicine()

        if (options.equals("2"))
            observeDataOdonto()


    }

    private fun setupRecyclerView(){
        adaptador = MainAdapter(this, this)
        binding.rv1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv1.adapter = adaptador

    }

    fun observeDataMedicine(){
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchDataMedicine().observe(this, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adaptador.setListData(it)
            adaptador.notifyDataSetChanged()
        })
    }

    fun observeDataOdonto(){
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchDataOdonto().observe(this, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adaptador.setListData(it)
            adaptador.notifyDataSetChanged()
        })
    }

    override fun onItemClick(id: String) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
    }
}