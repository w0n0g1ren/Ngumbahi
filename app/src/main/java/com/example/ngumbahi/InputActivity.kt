package com.example.ngumbahi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.ngumbahi.databinding.ActivityInputBinding
import com.example.ngumbahi.model.pesanan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()!!.hide() // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Pesanan")

        binding.btninput.setOnClickListener {

            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val current = LocalDateTime.now().format(formatter)

            val nama : String = binding.inputnama.text.toString()
            val berat : Int = binding.inputberat.text.toString().toInt()
            val paket = binding.inputtipe.selectedItem.toString()
            val tanggal = current.toString()
            val status = "proses"
            val telepon = binding.inputtelepon.text.toString()

            val hitung = hitung(berat,paket)
            val db = database.push().key!!
            val pesanan = pesanan(db,nama,berat,paket,tanggal,hitung,status,telepon)

            database.child(db).setValue(pesanan).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun hitung(berat: Int, paket: String): Int {
        var produk : Int = if(paket =="Kilat"){
            7000
        }else if (paket =="Syahdu"){
            9000
        }else if(paket =="Iron Men"){
            5000
        } else{

        } as Int

        val total : Int = produk.toString().toInt() * berat

        return total
    }


}

