package com.darksoft.arquitecturamvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darksoft.arquitecturamvvm.base.BaseViewHolder
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding
import com.darksoft.arquitecturamvvm.viewmodel.VistaClinicaDatos

class MainAdapter(private val context: Context, private val itemClickListener: onItemClickListener): RecyclerView.Adapter<BaseViewHolder<*>> (){

    private var dataList = mutableListOf<VistaClinicaDatos>()

    fun setListData(data: MutableList<VistaClinicaDatos>){
        dataList = data
    }

    interface onItemClickListener{
        fun onItemClick(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClinicasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ClinicasViewHolder -> holder.bind(dataList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0)
            dataList.size
        else
            0
    }

    inner class ClinicasViewHolder(val binding: ItemRowBinding): BaseViewHolder<VistaClinicaDatos>(binding){

        override fun bind(item: VistaClinicaDatos, position: Int) {

            Glide.with(context).load(item.image_url).into(binding.imageCard)
            binding.txtTitle.text = item.nombre_clinica
            binding.id.text = item.id

            //CLICK el item del recycler con su parametro para enviar a otra actividad
            binding.fotoCard.setOnClickListener{ itemClickListener.onItemClick(item.id) }
        }

    }



}