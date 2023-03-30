package com.example.ngumbahi.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.ngumbahi.LoginActivity
import com.example.ngumbahi.R
import com.example.ngumbahi.databinding.FragmentProfilBinding
import com.example.ngumbahi.model.pesanan
import com.google.firebase.auth.FirebaseAuth
import java.net.URLEncoder

class ProfilFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private  var _binding : FragmentProfilBinding? = null
    private val Binding get() = _binding!!
    lateinit var courseList: ArrayList<pesanan>
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
            Toast.makeText(context,"berhasil logout",Toast.LENGTH_LONG).show()
            activity?.finish()
        }

        return Binding.root

    }


}