package com.example.aplikasiregistrasiseminar

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SeminarActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var cbHobi1: CheckBox
    private lateinit var cbHobi2: CheckBox
    private lateinit var cbHobi3: CheckBox
    private lateinit var cbHobi4: CheckBox
    private lateinit var cbHobi5: CheckBox
    private lateinit var spinnerSeminar: Spinner
    private lateinit var cbAgreement: CheckBox
    private lateinit var btnDaftar: Button

    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seminar)

        session = SessionManager(this)

        etName = findViewById(R.id.etNamaForm)
        etEmail = findViewById(R.id.etEmailForm)
        etPhone = findViewById(R.id.etPhoneForm)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        cbHobi1 = findViewById(R.id.cbHobi1)
        cbHobi2 = findViewById(R.id.cbHobi2)
        cbHobi3 = findViewById(R.id.cbHobi3)
        cbHobi4 = findViewById(R.id.cbHobi4)
        cbHobi5 = findViewById(R.id.cbHobi5)
        spinnerSeminar = findViewById(R.id.spinnerSeminar)
        cbAgreement = findViewById(R.id.cbAgreement)
        btnDaftar = findViewById(R.id.btnSubmitRegistration)

        etName.setText(session.getName())
        etEmail.setText(session.getEmail())
        etPhone.setText(session.getPhone())

        setReadOnly(etName)
        setReadOnly(etEmail)
        setReadOnly(etPhone)

        val seminarList = listOf(
            "Pilih seminar",
            "Seminar Digital Marketing",
            "Seminar Public Speaking",
            "Seminar UI/UX Design",
            "Seminar Artificial Intelligence",
            "Seminar Kewirausahaan"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            seminarList
        )
        spinnerSeminar.adapter = adapter

        btnDaftar.setOnClickListener {
            val gender = getSelectedGender()
            val seminar = spinnerSeminar.selectedItem.toString()

            if (gender.isBlank()) {
                showWarning("Pilih jenis kelamin terlebih dahulu.")
                return@setOnClickListener
            }

            if (seminar == "Pilih seminar") {
                showWarning("Pilih seminar terlebih dahulu.")
                return@setOnClickListener
            }

            if (!cbAgreement.isChecked) {
                showWarning("Centang persetujuan terlebih dahulu.")
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data yang Anda isi sudah benar?")
                .setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this, ResultActivity::class.java).apply {
                        putExtra("nama", session.getName())
                        putExtra("email", session.getEmail())
                        putExtra("phone", session.getPhone())
                        putExtra("gender", gender)
                        putExtra("seminar", seminar)
                    }
                    startActivity(intent)
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    private fun getSelectedGender(): String {
        val selectedId = radioGroupGender.checkedRadioButtonId
        return if (selectedId == -1) {
            ""
        } else {
            findViewById<RadioButton>(selectedId).text.toString()
        }
    }

    private fun setReadOnly(editText: EditText) {
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isCursorVisible = false
        editText.keyListener = null
    }

    private fun showWarning(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Peringatan")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}