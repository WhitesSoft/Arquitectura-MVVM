package com.darksoft.arquitecturamvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<VistaClinicaDatos>()

    fun setListData(data: MutableList<VistaClinicaDatos>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val datos: VistaClinicaDatos = dataList[position]
        holder.bindView(datos)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0)
            dataList.size
        else
            0
    }

    inner class MainViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(datos: VistaClinicaDatos){
            Glide.with(context).load(datos.image_url).into(binding.imageCard)
            binding.txtTitle.text = datos.nombre_clinica
        }

    }

}