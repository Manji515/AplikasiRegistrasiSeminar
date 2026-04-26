package com.example.aplikasiregistrasiseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnRegister: Button
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        session = SessionManager(this)

        etName = findViewById(R.id.etNameRegister)
        etEmail = findViewById(R.id.etEmailRegister)
        etPassword = findViewById(R.id.etPasswordRegister)
        etConfirmPassword = findViewById(R.id.etConfirmPasswordRegister)
        etPhone = findViewById(R.id.etPhoneRegister)
        btnRegister = findViewById(R.id.btnRegisterSubmit)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            when {
                name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        confirmPassword.isEmpty() || phone.isEmpty() -> {
                    showWarning("Semua input harus diisi.")
                }

                !email.contains("@") -> {
                    showWarning("Email harus mengandung @.")
                }

                password != confirmPassword -> {
                    showWarning("Konfirmasi password tidak cocok.")
                }

                !isValidPhone(phone) -> {
                    showWarning("Nomor HP harus diawali 08 dan panjang 10–13 digit.")
                }

                else -> {
                    session.saveUser(name, email, password, phone)

                    AlertDialog.Builder(this)
                        .setTitle("Registrasi Berhasil")
                        .setMessage("Data berhasil disimpan. Silakan login.")
                        .setPositiveButton("OK") { _, _ ->
                            startActivity(
                                Intent(this, LoginActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                            )
                            finish()
                        }
                        .show()
                }
            }
        }
    }

    private fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^08\\d{8,11}$"))
    }

    private fun showWarning(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Peringatan")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}