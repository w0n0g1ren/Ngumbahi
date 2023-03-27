package com.example.ngumbahi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ngumbahi.LoginActivity
import com.example.ngumbahi.R
import com.example.ngumbahi.databinding.FragmentPesananBinding
import com.example.ngumbahi.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth

class ProfilFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private  var _binding : FragmentProfilBinding? = null
    private val Binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        Binding.btnlogout.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return Binding.root

    }
}