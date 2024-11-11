package com.huaranga.dayana.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huaranga.dayana.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesRepository = SharedPreferencesRepository().also {
            it.setSharedPreference(this)
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnBackClose.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    fun loginUser() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun registerUser() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val confirmPassword = binding.edtPassword2.text.toString()

        if (!isValidEmail(email)) {
            showToast("Correo electrónico inválido")
            return
        }

        if (password.length < 8) {
            showToast("La contraseña debe tener al menos 8 caracteres")
            return
        }

        if (password != confirmPassword) {
            showToast("Las contraseñas no coinciden")
            return
        }

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)

        showToast("Registro exitoso")
        navigateToLogin()
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}