package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    fun inicio(view: View){
        var intent = Intent(this,InicioSesion::class.java)
        finish()
        startActivity(intent)
    }
    fun registro(view: View){
        var intent = Intent(this,RegistroUsuarios::class.java)
        finish()
        startActivity(intent)
    }
}