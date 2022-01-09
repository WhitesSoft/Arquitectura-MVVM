package com.darksoft.arquitecturamvvm.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darksoft.arquitecturamvvm.R
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding
import com.darksoft.arquitecturamvvm.databinding.ItemTitleBinding

class HomeRecyclerAdapter(): RecyclerView.Adapter<HomeRecyclerViewHolder> (){

    private var dataList = mutableListOf<HomeRecyclerViewItem>()

    var items = listOf<HomeRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClick: ((view: View, item: HomeRecyclerViewItem, position: Int ) -> Unit)? = null

    fun setListData(data: MutableList<HomeRecyclerViewItem>){
        dataList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder{
//        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ClinicasViewHolder(binding)

        return when(viewType){
            R.layout.item_title -> HomeRecyclerViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            R.layout.item_row -> HomeRecyclerViewHolder.ClinicsViewHolder(
                ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            else -> throw IllegalArgumentException("Tipo de vista invalido")
        }

    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {

        holder.itemClick = itemClick

        when(holder){
            is HomeRecyclerViewHolder.ClinicsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Clinics)
            is HomeRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Title)
        }
    }

    override fun getItemCount() = items.size

    //Obtenemos la posicion de la vista
    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeRecyclerViewItem.Clinics -> R.layout.item_row
            is HomeRecyclerViewItem.Title -> R.layout.item_title

        }
    }

}