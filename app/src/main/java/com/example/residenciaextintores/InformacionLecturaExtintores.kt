package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class InformacionLecturaExtintores : AppCompatActivity() {
    var et_id: EditText? = null
    var et_folio: EditText? = null
    var et_capacidad: EditText? = null
    var et_tipo: EditText? = null
    var et_ubicacion: EditText? = null
    var et_seguro: EditText? = null
    var et_recargado: EditText? = null
    var IdExtintor:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_lectura_extintores)
        et_id = findViewById(R.id.et_id)
        et_folio = findViewById(R.id.et_folio)
        et_capacidad = findViewById(R.id.et_capacidad)
        et_tipo = findViewById(R.id.et_tipo)
        et_ubicacion = findViewById(R.id.et_ubicacion)
        et_seguro = findViewById(R.id.et_seguro)
        et_recargado = findViewById(R.id.et_recargado)

        IdExtintor = intent.getStringExtra("IdExtintor").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registro.php?IdExtintor=$IdExtintor"
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
            },
            { error ->
                Toast.makeText(this, "No existen extintores. $error", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
    }
    fun salir(view: View){
        var intent = Intent(this,MenuBasico::class.java)
        finish()
        startActivity(intent)
    }
}