package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var et_id:EditText?=null
    var et_folio:EditText?=null
    var et_capacidad:EditText?=null
    var et_tipo:EditText?=null
    var et_ubicacion:EditText?=null
    var et_seguro:EditText?=null
    var et_recargado:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_id=findViewById(R.id.et_id)
        et_folio=findViewById(R.id.et_folio)
        et_capacidad=findViewById(R.id.et_capacidad)
        et_tipo=findViewById(R.id.et_tipo)
        et_ubicacion=findViewById(R.id.et_ubicacion)
        et_seguro=findViewById(R.id.et_seguro)
        et_recargado=findViewById(R.id.et_recargado)
    }
    fun insert(view:View) {
        if (et_id?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce id.", Toast.LENGTH_LONG).show()
        }
        else if (et_folio?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce folio del extintor..", Toast.LENGTH_LONG).show()
        }
        else if (et_capacidad?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce capacidad del extintor.", Toast.LENGTH_LONG).show()
        }
        else if (et_tipo?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce tipo de extintor.", Toast.LENGTH_LONG).show()
        }
        else if (et_ubicacion?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce ubicación del extintor.", Toast.LENGTH_LONG).show()
        }
        else if (et_seguro?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Indica si el seguro esta puesto", Toast.LENGTH_LONG).show()
        }
        else if (et_recargado?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Indica si el exintor ha sido recargado.", Toast.LENGTH_LONG).show()
        }
        else {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/insert.php"
            val queue = Volley.newRequestQueue(this)
            var resultPOST = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "Extintor añadido exitosamete.", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,ActividadesExtintores::class.java)
                    finish()
                    startActivity(intent)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error al añadir el extintor.$error", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametro = HashMap<String, String>()
                    parametro.put("IdExtintor", et_id?.text.toString())
                    parametro.put("folio", et_folio?.text.toString())
                    parametro.put("capacidad", et_capacidad?.text.toString())
                    parametro.put("tipo", et_tipo?.text.toString())
                    parametro.put("ubicacion", et_ubicacion?.text.toString())
                    parametro.put("seguro", et_seguro?.text.toString())
                    parametro.put("recargado", et_recargado?.text.toString())
                    return parametro
                }
            }
            queue.add(resultPOST)
        }
    }
    fun dirigir(view: View){
        var intent = Intent(this,ActividadesExtintores::class.java)
        startActivity(intent)
    }
}