package com.example.ngumbahi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngumbahi.R
import com.example.ngumbahi.model.pesanan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.snapshots

class tampiladapter(private val model: List<pesanan>,var snapshot: String) : RecyclerView.Adapter<tampiladapter.ViewHolder> () {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama : TextView = itemView.findViewById(R.id.tampilnama)
        var berat : TextView = itemView.findViewById(R.id.tampilberat)
        var paket : TextView = itemView.findViewById(R.id.tampiljenis)
        var total : TextView = itemView.findViewById(R.id.tampiltotal)
        var tanggal : TextView = itemView.findViewById(R.id.tampiltanggal)

        var selesai : Button = itemView.findViewById(R.id.btnselesai)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.tampil,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = model[position]

        holder.nama.text = list.nama
        holder.berat.text = list.berat.toString()
        holder.paket.text = list.paket
        holder.total.text = list.total.toString()
        holder.tanggal.text = list.tanggal


        holder.selesai.setOnClickListener {
            val selesai : String = "selesai"
            val editmap = mapOf(
                "berat" to list.berat.toString().toInt(),
                "nama"  to list.nama,
                "paket" to list.paket,
                "status" to selesai,
                "tanggal" to list.tanggal,
                "total" to list.total.toString().toInt()
            )
            val database : DatabaseReference

            database = FirebaseDatabase.getInstance().getReference("Pesanan").child("$snapshot")
            database.orderByChild("nama").equalTo(list.nama)
            database.updateChildren(editmap)

        }
    }
    override fun getItemCount(): Int {
        return model.size
    }
}