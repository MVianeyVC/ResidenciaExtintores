package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun consultar_extintor(view: View){
        var intent = Intent(this,ActividadesExtintores::class.java)
        startActivity(intent)
    }
    fun añadir_extintor(view: View){
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}