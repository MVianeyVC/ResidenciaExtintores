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

class ProveedorExtintores : AppCompatActivity() {
    var et_nombre: EditText? = null
    var et_direccion: EditText? = null
    var et_telefono: EditText? = null
    var et_RFC: EditText? = null
    var et_municipio_estado: EditText? = null
    var et_cp: EditText? = null
    var et_IdUniversidad: EditText? = null
    var et_IdExtintor: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor_extintores)
        et_nombre=findViewById(R.id.et_nombre)
        et_direccion=findViewById(R.id.et_direccion)
        et_telefono=findViewById(R.id.et_telefono)
        et_RFC=findViewById(R.id.et_rfc)
        et_municipio_estado=findViewById(R.id.et_municipio_estado)
        et_cp=findViewById(R.id.et_cp)
        et_IdUniversidad=findViewById(R.id.et_id_universidad)
        et_IdExtintor=findViewById(R.id.et_id_extintor)
    }
    fun insertU(view: View) {
        if (et_nombre?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce nombre.", Toast.LENGTH_LONG).show()
        }
        else if (et_direccion?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce dirección.", Toast.LENGTH_LONG).show()
        }
        else if (et_telefono?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce número telefonico.", Toast.LENGTH_LONG).show()
        }
        else if (et_RFC?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce RFC.", Toast.LENGTH_LONG).show()
        }
        else if (et_municipio_estado?.getText().toString().isEmpty()){
            Toast.makeText(this,"Introduce Municipio y/o estado.",Toast.LENGTH_LONG).show()
        }
        else if (et_cp?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce código postal.", Toast.LENGTH_LONG).show()
        }
        else if (et_IdUniversidad?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce número de universidad.", Toast.LENGTH_LONG).show()
        }
        else if (et_IdExtintor?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce número de extintor.", Toast.LENGTH_LONG).show()
        }
        else {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/insercionproveedor.php"
            val queue = Volley.newRequestQueue(this)
            var resultPOST = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "Proveedor registrado exitosamete.", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,ActividadesProveedores::class.java)
                    finish()
                    startActivity(intent)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Error al añadir proveedor.$error", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametro = HashMap<String, String>()
                    parametro.put("nombre", et_nombre?.text.toString())
                    parametro.put("direccion", et_direccion?.text.toString())
                    parametro.put("telefono", et_telefono?.text.toString())
                    parametro.put("RFC", et_RFC?.text.toString())
                    parametro.put("municipio_estado", et_municipio_estado?.text.toString())
                    parametro.put("cp", et_cp?.text.toString())
                    parametro.put("IdUniversidad", et_IdUniversidad?.text.toString())
                    parametro.put("IdExtintor", et_IdExtintor?.text.toString())

                    return parametro
                }
            }
            queue.add(resultPOST)
        }
    }
}