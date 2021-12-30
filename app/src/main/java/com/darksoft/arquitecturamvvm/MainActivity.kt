package com.darksoft.arquitecturamvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.darksoft.arquitecturamvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptador: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.tool))

        adaptador = MainAdapter(this)
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = adaptador



        val datos = mutableListOf<VistaDatos>()
        datos.add(VistaDatos("https://cdn.pixabay.com/photo/2016/03/08/20/03/flag-1244648_960_720.jpg",
                            "CLINICA ALEMANA"))
        datos.add(VistaDatos("https://cdn.pixabay.com/photo/2018/04/26/12/14/travel-3351825__340.jpg",
            "CLINICA BRASIL"))

        datos.add(VistaDatos("https://cdn.pixabay.com/photo/2021/03/12/08/51/shorturl-6089108__340.jpg",
            "CAJA DE SALUD DE LA BANCA PRIVADA"))

        adaptador.setListData(datos)
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