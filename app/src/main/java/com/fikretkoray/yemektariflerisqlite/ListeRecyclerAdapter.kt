package com.fikretkoray.yemektariflerisqlite

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fikretkoray.yemektariflerisqlite.databinding.RecyclerRowBinding

class ListeRecyclerAdapter(val yemekListesi: ArrayList<String>, val idListesi: ArrayList<Int>): RecyclerView.Adapter<ListeRecyclerAdapter.YemekHolder>() {
    class YemekHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return YemekHolder(binding)
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
        holder.binding.recyclerRowText.text = yemekListesi[position]
        holder.itemView.setOnClickListener{
            val action = ListeFragmentDirections.actionListeFragmentToTarifFragment("recyclerdangeldim",idListesi[position])
            Navigation.findNavController(it).navigate(action)
        }
    }
}