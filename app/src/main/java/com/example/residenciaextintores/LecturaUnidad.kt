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

class LecturaUnidad : AppCompatActivity() {
    var et_nombre: EditText? = null
    var et_direccion: EditText? = null
    var et_municipio_estado: EditText? = null
    var et_cp: EditText? = null
    var et_unidad: EditText? = null
    var et_departamento: EditText? = null
    var IdUniversidad:String? = null
    var et_id: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectura_unidad)
        et_nombre=findViewById(R.id.et_nombre)
        et_direccion=findViewById(R.id.et_direccion)
        et_municipio_estado=findViewById(R.id.et_municipio_estado)
        et_cp=findViewById(R.id.et_cp)
        et_unidad=findViewById(R.id.et_unidad)
        et_departamento=findViewById(R.id.et_departamento)
        et_id=findViewById(R.id.et_id)

        // IdUniversidad = intent.getStringExtra("IdUniversidad").toString()

        val queue = Volley.newRequestQueue(this)

        //val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registrouniversidad.php?IdUniversidad=$IdUniversidad"
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registrouniversidad.php?"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                et_id?.setText(response.getString("IdUniversidad"))
                et_nombre?.setText(response.getString("nombre"))
                et_direccion?.setText(response.getString("direccion"))
                et_municipio_estado?.setText(response.getString("municipio_estado"))
                et_cp?.setText(response.getString("cp"))
                et_unidad?.setText(response.getString("unidad"))
                et_departamento?.setText(response.getString("departamento"))
            },
            { error ->
                Toast.makeText(this, "No existe universidad. $error", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun salir(view: View){
        var intent = Intent(this,MenuBasico::class.java)
        finish()
        startActivity(intent)
    }
}