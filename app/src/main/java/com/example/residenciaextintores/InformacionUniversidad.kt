package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class InformacionUniversidad : AppCompatActivity() {
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
        setContentView(R.layout.activity_informacion_universidad)
        et_nombre=findViewById(R.id.et_nombre)
        et_direccion=findViewById(R.id.et_direccion)
        et_municipio_estado=findViewById(R.id.et_municipio_estado)
        et_cp=findViewById(R.id.et_cp)
        et_unidad=findViewById(R.id.et_unidad)
        et_departamento=findViewById(R.id.et_departamento)
        et_id=findViewById(R.id.et_id)

        IdUniversidad = intent.getStringExtra("IdUniversidad").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registrouniversidad.php?IdUniversidad=$IdUniversidad"
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
    fun borrar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/borraruniversidad.php"
        val queue = Volley.newRequestQueue(this)
        var resultado = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this, "Universidad borrado exitosamente.", Toast.LENGTH_LONG).show()
            },
            { error ->
                Toast.makeText(this, "Error al borrar universidad. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("IdUniversidad", IdUniversidad!!)
                return parametros
            }
        }
        queue.add(resultado)
    }
    fun editar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editaruniversidad.php"
        val queue = Volley.newRequestQueue(this)
        val resultado = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(this, "Universidad editado exitosamente.", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesUniversidad::class.java)
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this, "Error al editar universidad. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("IdUniversidad", IdUniversidad!!)
                parametros.put("nombre", et_nombre?.text.toString())
                parametros.put("direccion", et_direccion?.text.toString())
                parametros.put("municipio_estado", et_municipio_estado?.text.toString())
                parametros.put("cp", et_cp?.text.toString())
                parametros.put("unidad", et_unidad?.text.toString())
                parametros.put("departamento", et_departamento?.text.toString())
                return parametros
            }
        }
        queue.add(resultado)
    }
}