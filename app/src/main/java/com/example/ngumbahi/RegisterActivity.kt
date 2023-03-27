package com.example.ngumbahi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ngumbahi.databinding.ActivityRegisterBinding
import com.example.ngumbahi.model.akun
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.math.BigInteger
import java.security.MessageDigest


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Akun")
        binding.buttonregister.setOnClickListener {
            var email = binding.emailregister.text.toString()
            var nama = binding.namaregister.text.toString()
            var phone = binding.phoneregister.text.toString()
            var password = binding.passwordregister.text.toString()

            var md5password = md5(password)
            val db = database.push().key!!
            val akun = akun(email,nama,phone,md5password)
            database.child(db).setValue(akun)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this,LoginActivity::class.java)
                        Toast.makeText(this, "Input berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
    fun md5 (string: String) : String{
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(string.toByteArray())).toString(16).padStart(32, '0')
    }
}