package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuBasico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_basico)
    }
    fun consultar_mantenimientos(view: View){
        var intent = Intent(this,LecturaMantenimiento::class.java)
        startActivity(intent)
    }
    fun consultar_extintores(view: View){
        var intent = Intent(this,LecturaMantenimiento::class.java)
        startActivity(intent)
    }
    fun consultar_unidad(view: View){
        var intent = Intent(this,LecturaUnidad::class.java)
        startActivity(intent)
    }
}