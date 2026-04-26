package com.example.aplikasiregistrasiseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btnDaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnDaftar = findViewById(R.id.btnDaftarSeminar)
        btnDaftar.setOnClickListener {
            startActivity(Intent(this, SeminarActivity::class.java))
        }
    }
}