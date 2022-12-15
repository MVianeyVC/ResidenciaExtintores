package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuBonito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_bonito)
    }
    fun consultar_extintor(view: View) {
        var intent = Intent(this, ActividadesExtintores::class.java)
        startActivity(intent)
    }
    fun añadir_extintor(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun añadir_mantenimiento(view: View) {
        var intent = Intent(this, Mantenimiento::class.java)
        startActivity(intent)
    }
    fun consultar_mantenimiento(view: View) {
        var intent = Intent(this, ActividadesMantenimiento::class.java)
        startActivity(intent)
    }
    fun añadir_proveedor(view: View) {
        var intent = Intent(this, ProveedorExtintores::class.java)
        startActivity(intent)
    }
    fun consultar_proveedor(view: View) {
        var intent = Intent(this, ActividadesProveedores::class.java)
        startActivity(intent)
    }
    fun info_universidad(view: View) {
        var intent = Intent(this, InformacionUniversidad::class.java)
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
    fun editar_usuario(view: View) {
        var intent = Intent(this, ActividadesUsuarios::class.java)
        startActivity(intent)
    }
    fun reportes(view: View) {
        var intent = Intent(this, Reportes::class.java)
        startActivity(intent)
    }
}