package com.example.ngumbahi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngumbahi.R
import com.example.ngumbahi.model.pesanan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class tampiladapter2(private var model: List<pesanan>) : RecyclerView.Adapter<tampiladapter2.ViewHolder> () {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama : TextView = itemView.findViewById(R.id.tampilnama2)
        var berat : TextView = itemView.findViewById(R.id.tampilberat2)
        var paket : TextView = itemView.findViewById(R.id.tampiljenis2)
        var total : TextView = itemView.findViewById(R.id.tampiltotal2)
        var tanggal : TextView = itemView.findViewById(R.id.tampiltanggal2)
        var telepon : TextView = itemView.findViewById(R.id.tampiltelepon2)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.tampil_selesai,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = model[position]

        holder.nama.text = list.nama
        holder.berat.text = list.berat.toString()
        holder.paket.text = list.paket
        holder.total.text = list.total.toString()
        holder.tanggal.text = list.tanggal
        holder.telepon.text = list.telepon

    }
    override fun getItemCount(): Int {
        return model.size
    }
}