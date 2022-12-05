package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Universidad : AppCompatActivity() {
    var et_nombre: EditText? = null
    var et_direccion: EditText? = null
    var et_municipio_estado: EditText? = null
    var et_cp: EditText? = null
    var et_unidad: EditText? = null
    var et_departamento: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_universidad)
        et_nombre=findViewById(R.id.et_nombre)
        et_direccion=findViewById(R.id.et_direccion)
        et_municipio_estado=findViewById(R.id.et_municipio_estado)
        et_cp=findViewById(R.id.et_cp)
        et_unidad=findViewById(R.id.et_unidad)
        et_departamento=findViewById(R.id.et_departamento)
    }
    fun insertU(view: View) {
        if (et_nombre?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce nombre.", Toast.LENGTH_LONG).show()
        }
        else if (et_direccion?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce dirección.", Toast.LENGTH_LONG).show()
        }
        else if (et_municipio_estado?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce municipio y/o estado.", Toast.LENGTH_LONG).show()
        }
        else if (et_cp?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce código postal.", Toast.LENGTH_LONG).show()
        }
        else if (et_unidad?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce nombre de la unidad.", Toast.LENGTH_LONG).show()
        }
        else if (et_departamento?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Indica el departamento.", Toast.LENGTH_LONG).show()
        }
        else {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/insercionuniversidad.php"
            val queue = Volley.newRequestQueue(this)
            var resultPOST = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "Universidad registrada exitosamete.", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,Menu::class.java)
                    finish()
                    startActivity(intent)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error al añadir universidad.$error", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametro = HashMap<String, String>()
                    parametro.put("nombre", et_nombre?.text.toString())
                    parametro.put("direccion", et_direccion?.text.toString())
                    parametro.put("municipio_estado", et_municipio_estado?.text.toString())
                    parametro.put("cp", et_cp?.text.toString())
                    parametro.put("unidad", et_unidad?.text.toString())
                    parametro.put("departamento", et_departamento?.text.toString())
                    return parametro
                }
            }
            queue.add(resultPOST)
        }
    }
}