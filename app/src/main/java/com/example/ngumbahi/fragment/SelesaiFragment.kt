package com.example.ngumbahi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ngumbahi.InputActivity
import com.example.ngumbahi.R
import com.example.ngumbahi.adapter.tampiladapter
import com.example.ngumbahi.databinding.FragmentPesananBinding
import com.example.ngumbahi.databinding.FragmentProfilBinding
import com.example.ngumbahi.databinding.FragmentSelesaiBinding
import com.example.ngumbahi.model.pesanan
import com.google.firebase.database.*

class SelesaiFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var userArrayList: ArrayList<pesanan>
    private  var _binding : FragmentSelesaiBinding? = null
    private val Binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun getpesananData() {

        userArrayList = arrayListOf<pesanan>()
        database = FirebaseDatabase.getInstance().getReference("Pesanan")
        database.orderByChild("status").equalTo("selesai").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val pesanan = userSnapshot.getValue(pesanan::class.java)
                        userArrayList.add(pesanan!!)
                    }
                    Binding.rvtampilselesai.adapter = tampiladapter(userArrayList,database.toString())
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
        _binding = FragmentSelesaiBinding.inflate(inflater, container, false)
        _binding?.rvtampilselesai?.apply {
            layoutManager = LinearLayoutManager(activity)

        }
        getpesananData()
        return Binding.root

    }
}