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
    fun añadir_mantenimiento(view: View){
        var intent = Intent(this,Mantenimiento::class.java)
        startActivity(intent)
    }
    fun consultar_mantenimiento(view: View) {
        var intent = Intent(this, ActividadesMantenimiento::class.java)
        startActivity(intent)
    }
    fun añadir_universidad(view: View) {
        var intent = Intent(this, Universidad::class.java)
        startActivity(intent)
    }
    fun consultar_universidad(view: View) {
        var intent = Intent(this, ActividadesUniversidad::class.java)
        startActivity(intent)
    }
}