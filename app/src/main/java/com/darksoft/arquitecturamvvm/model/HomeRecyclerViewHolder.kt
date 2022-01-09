package com.darksoft.arquitecturamvvm.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding
import com.darksoft.arquitecturamvvm.databinding.ItemTitleBinding

sealed class HomeRecyclerViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    var itemClick: ((view: View, item: HomeRecyclerViewItem, position: Int ) -> Unit)? = null

    class TitleViewHolder(private val binding: ItemTitleBinding): HomeRecyclerViewHolder(binding){
        fun bind(title: HomeRecyclerViewItem.Title){
            binding.titleClinic.text = title.title
        }
    }

    class ClinicsViewHolder(private val binding: ItemRowBinding): HomeRecyclerViewHolder(binding){
        fun bind(clinics: HomeRecyclerViewItem.Clinics){
            Glide.with(binding.root).load(clinics.image_url).into(binding.imageCard)
            binding.txtTitle.text = clinics.nombre_clinica
            binding.id.text = clinics.id

            binding.fotoCard.setOnClickListener {
                itemClick?.invoke(it, clinics, adapterPosition)
            }

            //CLICK el item del recycler con su parametro para enviar a otra actividad
            //binding.fotoCard.setOnClickListener{ itemClickListener.onItemClick(item.id) }
        }
    }

}