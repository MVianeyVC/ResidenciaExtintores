package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class InformacionReporte : AppCompatActivity() {
    var et_id: EditText? = null
    var et_folio: EditText? = null
    var et_capacidad: EditText? = null
    var et_tipo: EditText? = null
    var et_ubicacion: EditText? = null
    var et_seguro: EditText? = null
    var et_recargado: EditText? = null
    var et_fechaI: EditText? = null
    var et_fechaF: EditText? = null
    var et_id_proveedor:EditText? = null
    var et_nombre:EditText? = null
    var IdExtintor:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_reporte)
        et_id = findViewById(R.id.et_id)
        et_folio = findViewById(R.id.et_folio)
        et_capacidad = findViewById(R.id.et_capacidad)
        et_tipo = findViewById(R.id.et_tipo)
        et_ubicacion = findViewById(R.id.et_ubicacion)
        et_seguro = findViewById(R.id.et_seguro)
        et_recargado = findViewById(R.id.et_recargado)
        et_fechaI= findViewById(R.id.et_fechaI)
        et_fechaF= findViewById(R.id.et_fechaF)
        et_id_proveedor= findViewById(R.id.et_id_proveedor)
        et_nombre= findViewById(R.id.et_nombreproveedor)

        IdExtintor = intent.getStringExtra("IdExtintor").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registroreportes.php?IdExtintor=$IdExtintor"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                et_id?.setText(response.getString("IdExtintor"))
                et_folio?.setText(response.getString("folio"))
                et_capacidad?.setText(response.getString("capacidad"))
                et_tipo?.setText(response.getString("tipo"))
                et_ubicacion?.setText(response.getString("ubicacion"))
                et_seguro?.setText(response.getString("seguro"))
                et_recargado?.setText(response.getString("recargado"))
                et_fechaI?.setText(response.getString("fecha_activo"))
                et_fechaF?.setText(response.getString("fecha_vecimiento"))
                et_id_proveedor?.setText(response.getString("IdProveedor"))
                et_nombre?.setText(response.getString("nombre"))
            },
            { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }
}