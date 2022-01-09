package com.darksoft.arquitecturamvvm.model

sealed class HomeRecyclerViewItem {

    class Title(val id: String, val title: String): HomeRecyclerViewItem()

    class Clinics(val id: String, val image_url: String, val nombre_clinica: String): HomeRecyclerViewItem()

}