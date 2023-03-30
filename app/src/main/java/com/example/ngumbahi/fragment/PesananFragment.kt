package com.example.ngumbahi.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ngumbahi.InputActivity
import com.example.ngumbahi.MainActivity
import com.example.ngumbahi.adapter.tampiladapter
import com.example.ngumbahi.databinding.FragmentPesananBinding
import com.example.ngumbahi.model.pesanan
import com.google.firebase.database.*

class PesananFragment : Fragment() {


    private lateinit var database: DatabaseReference
    private lateinit var userArrayList: ArrayList<pesanan>
    private  var _binding : FragmentPesananBinding? = null
    private val Binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun getpesananData() {

        userArrayList = arrayListOf<pesanan>()
        database = FirebaseDatabase.getInstance().getReference("Pesanan")
        database.orderByChild("status").equalTo("proses").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val pesanan = userSnapshot.getValue(pesanan::class.java)
                        userArrayList.add(pesanan!!)

                        Binding.rvtampil.adapter = tampiladapter(context as MainActivity,userArrayList)
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPesananBinding.inflate(inflater, container, false)
        _binding?.rvtampil?.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        _binding?.btntambah?.setOnClickListener {
            val intent = Intent(context,InputActivity::class.java)
            startActivity(intent)
        }
        getpesananData()
        return Binding.root

    }




}