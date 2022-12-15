package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class InformacionLecturaMantenimiento : AppCompatActivity() {
    /* Declaracion de los identificadores segun el tipo del xml en null. */
    var et_idman: EditText? = null
    var et_fechaI: EditText? = null
    var et_fechaF: EditText? = null
    var et_id: EditText? = null
    var IdMantenimiento:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_lectura_mantenimiento)
        et_idman = findViewById(R.id.et_idman)
        et_fechaI = findViewById(R.id.et_fechaI)
        et_fechaF = findViewById(R.id.et_fechaF)
        et_id = findViewById(R.id.et_id)

        IdMantenimiento = intent.getStringExtra("IdMantenimiento").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registromantenimiento.php?IdMantenimiento=$IdMantenimiento"
        val jsonObjectRequest = JsonObjectRequest( Request.Method.GET, url, null,
            { response ->
                et_idman?.setText(response.getString("IdMantenimiento"))
                et_fechaI?.setText(response.getString("fecha_activo"))
                et_fechaF?.setText(response.getString("fecha_vecimiento"))
                et_id?.setText(response.getString("IdExtintor"))
            },
            {
                    error ->
                Toast.makeText(this, "No existen mantenimietos. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,LecturaMantenimiento::class.java)
                finish()
                startActivity(intent)
            })
        queue.add(jsonObjectRequest)
    }
    fun salir(view: View){
        var intent = Intent(this,MenuBasico::class.java)
        finish()
        startActivity(intent)
    }
}