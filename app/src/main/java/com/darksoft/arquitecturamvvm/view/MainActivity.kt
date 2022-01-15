package com.darksoft.arquitecturamvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darksoft.arquitecturamvvm.model.HomeRecyclerAdapter
import com.darksoft.arquitecturamvvm.viewmodel.MainViewModel
import com.darksoft.arquitecturamvvm.R
import com.darksoft.arquitecturamvvm.databinding.ActivityMainBinding
import com.darksoft.arquitecturamvvm.model.HomeRecyclerViewItem


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private val clinics = HomeRecyclerAdapter()
    private val odonto = HomeRecyclerAdapter()
    private val consultorios = HomeRecyclerAdapter()

    private val viewModel by lazy{ ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.tool))

        setupRecyclerView()
        actionTxtViewAll()

        itemClicks()
    }

    private fun itemClicks() {
        clinics.itemClick = { view: View, item: HomeRecyclerViewItem, position: Int ->
            when(item){
                is HomeRecyclerViewItem.Clinics -> {
                    Toast.makeText(this, "clinica: ${item.id} clickeada", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, InformacionClinica::class.java)
                    intent.putExtra("id", item.id)
                    startActivity(intent)
                }
            }
        }

        odonto.itemClick = { view: View, item: HomeRecyclerViewItem, position: Int ->
            when(item){
                is HomeRecyclerViewItem.Clinics -> Toast.makeText(this, "clinica: ${item.nombre_clinica} clickeada",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun setupRecyclerView(){
        binding.rv1.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = clinics

            //observeData()
            binding.shimmerViewContainer.startShimmer()
            viewModel.fetchClinics().observeForever{
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE

                clinics.items = it
            }
        }

        binding.rv2.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = odonto

            viewModel.fetchOdonto().observeForever{
                odonto.items = it
            }

            //observeData()
        }

        binding.rv3.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = consultorios

            viewModel.fetchConsultorios().observeForever{
                consultorios.items = it
            }
            //observeData()
        }

        binding.rv4.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            //adapter = homeRecyclerAdapter

           // observeData()
        }

        binding.rv5.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
           // adapter = homeRecyclerAdapter

            //observeData()
        }

    }

    private fun observeData() {
        //binding.shimmerViewContainer.startShimmer()
//        viewModel.fetchData().observe(this) {
//          //  binding.shimmerViewContainer.stopShimmer()
//            //binding.shimmerViewContainer.visibility = View.GONE
//            homeRecyclerAdapter.items = it
//        }
    }

    private fun actionTxtViewAll(){
        binding.txtViewAll.setOnClickListener {
           val intent = Intent(this, ViewAllData::class.java)
            intent.putExtra( "clinicas", "1")
            startActivity(intent)
        }

        binding.txtViewAllDentalClinics.setOnClickListener {
            val intent = Intent(this, ViewAllData::class.java)
            intent.putExtra( "odonto", "2")
            startActivity(intent)
        }
    }



    //Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        R.id.buscar -> {
            Toast.makeText(this, "buscar", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}