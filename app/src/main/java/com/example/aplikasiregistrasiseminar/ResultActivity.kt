package com.example.aplikasiregistrasiseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvNama = findViewById<TextView>(R.id.tvResultNama)
        val tvEmail = findViewById<TextView>(R.id.tvResultEmail)
        val tvPhone = findViewById<TextView>(R.id.tvResultPhone)
        val tvGender = findViewById<TextView>(R.id.tvResultGender)
        val tvSeminar = findViewById<TextView>(R.id.tvResultSeminar)
        val btnBack = findViewById<Button>(R.id.btnBackHome)

        val nama = intent.getStringExtra("nama") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val seminar = intent.getStringExtra("seminar") ?: ""

        tvNama.text = "Nama: $nama"
        tvEmail.text = "Email: $email"
        tvPhone.text = "Nomor HP: $phone"
        tvGender.text = "Jenis Kelamin: $gender"
        tvSeminar.text = "Seminar: $seminar"

        btnBack.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
            )
            finish()
        }
    }
}