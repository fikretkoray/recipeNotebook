package com.fikretkoray.yemektariflerisqlite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikretkoray.yemektariflerisqlite.databinding.FragmentListeBinding


class ListeFragment : Fragment() {
    private lateinit var binding: FragmentListeBinding

    var yemekIsmiListesi = ArrayList <String>()
    var yemekIdListesi = ArrayList <Int>()
    private lateinit var listeAdapter : ListeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = ListeRecyclerAdapter(yemekIsmiListesi,yemekIdListesi)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = listeAdapter

        sqlVeriAlma()
    }

    fun sqlVeriAlma(){
        try {
            activity?.let {
                val database = it.openOrCreateDatabase("Yemekler",Context.MODE_PRIVATE,null)

                val cursor = database.rawQuery("SELECT * FROM yemekler",null)
                val yemekIsmiIndex = cursor.getColumnIndex("yemekismi")
                val yemekIdIndex = cursor.getColumnIndex("id")

                yemekIsmiListesi.clear()
                yemekIdListesi.clear()

                while (cursor.moveToNext()){
                    yemekIsmiListesi.add(cursor.getString(yemekIsmiIndex))
                    yemekIdListesi.add(cursor.getInt(yemekIdIndex))
                }

                listeAdapter.notifyDataSetChanged()

                cursor.close()
            }

        }catch (e: Exception){

        }
    }

    }