package com.example.ngumbahi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ngumbahi.R
import com.example.ngumbahi.model.pesanan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.snapshots

class tampiladapter(private val context: Context, private val model: List<pesanan>) : RecyclerView.Adapter<tampiladapter.ViewHolder> () {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama : TextView = itemView.findViewById(R.id.tampilnama)
        var berat : TextView = itemView.findViewById(R.id.tampilberat)
        var paket : TextView = itemView.findViewById(R.id.tampiljenis)
        var total : TextView = itemView.findViewById(R.id.tampiltotal)
        var tanggal : TextView = itemView.findViewById(R.id.tampiltanggal)
        var telepon : TextView = itemView.findViewById(R.id.tampiltelepon)
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
        holder.telepon.text = list.telepon


        holder.selesai.setOnClickListener {
            val selesai : String = "selesai"
            val editmap = mapOf(
                "berat" to list.berat.toString().toInt(),
                "nama"  to list.nama,
                "paket" to list.paket,
                "status" to selesai,
                "tanggal" to list.tanggal,
                "total" to list.total.toString().toInt(),
                "telepon" to list.telepon.toString()
            )

            var msg : String = "Pesananan anda atas nama ${list.nama} dengan paket ${list.paket} telah selesai"
            val database : DatabaseReference

            database = FirebaseDatabase.getInstance().getReference("Pesanan").child("${list.id}")
            database.updateChildren(editmap)

            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = Uri.parse("sms:${list.telepon}?body=$msg")
            context.startActivity(smsIntent)

        }
    }
    override fun getItemCount(): Int {
        return model.size
    }
}