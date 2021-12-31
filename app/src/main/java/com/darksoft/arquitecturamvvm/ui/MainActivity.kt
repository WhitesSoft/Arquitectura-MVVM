package com.darksoft.arquitecturamvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darksoft.arquitecturamvvm.MainAdapter
import com.darksoft.arquitecturamvvm.viewmodel.MainViewModel
import com.darksoft.arquitecturamvvm.R
import com.darksoft.arquitecturamvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptador: MainAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.tool))

        adaptador = MainAdapter(this)
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = adaptador

        observeData()
    }

    fun observeData(){
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchClinicaData().observe(this, Observer {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adaptador.setListData(it)
            adaptador.notifyDataSetChanged()
        })
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