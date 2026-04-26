package com.example.aplikasiregistrasiseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: Button
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = SessionManager(this)

        etName = findViewById(R.id.etNameLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        btnRegister = findViewById(R.id.btnGoRegister)
        btnLogin = findViewById(R.id.btnLogin)

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val name = etName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (!session.hasRegisteredUser()) {
                showWarning("Belum ada data terdaftar. Silakan register terlebih dahulu.")
                return@setOnClickListener
            }

            if (name == session.getName() && password == session.getPassword()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                showWarning("Nama atau password salah.")
            }
        }
    }

    private fun showWarning(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Peringatan")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}