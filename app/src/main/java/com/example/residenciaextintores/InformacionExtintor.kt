package com.example.residenciaextintores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class InformacionExtintor : AppCompatActivity() {
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
        setContentView(R.layout.activity_informacion_extintor)
        et_id = findViewById(R.id.et_id)
        et_folio = findViewById(R.id.et_folio)
        et_capacidad = findViewById(R.id.et_capacidad)
        et_tipo = findViewById(R.id.et_tipo)
        et_ubicacion = findViewById(R.id.et_ubicacion)
        et_seguro = findViewById(R.id.et_seguro)
        et_recargado = findViewById(R.id.et_recargado)

        IdExtintor = intent.getStringExtra("IdExtintor").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registro.php?IdExtintor=${IdExtintor}"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
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
                Toast.makeText(this, "No existe extintor con ese ID. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesExtintores::class.java)
                finish()
                startActivity(intent)
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun borrar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/borrar.php"
        val queue = Volley.newRequestQueue(this)
        var resultado = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this, "Extintor borrado exitosamente.", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesExtintores::class.java)
                finish()
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this, "Error al borrar extintor. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesExtintores::class.java)
                finish()
                startActivity(intent)
            }
        )
        {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("IdExtintor", IdExtintor!!)
                return parametros
            }
        }
        queue.add(resultado)
    }
    fun editar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editar.php"
        val queue = Volley.newRequestQueue(this)
        val resultado = object : StringRequest(Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(this, "Extintor editado exitosamente.", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesExtintores::class.java)
                finish()
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this, "Error al editar extintor. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("IdExtintor", IdExtintor!!)
                parametros.put("folio", et_folio?.text.toString())
                parametros.put("capacidad", et_capacidad?.text.toString())
                parametros.put("tipo", et_tipo?.text.toString())
                parametros.put("ubicacion", et_ubicacion?.text.toString())
                parametros.put("seguro", et_seguro?.text.toString())
                parametros.put("recargado", et_recargado?.text.toString())
                return parametros
            }
        }
        queue.add(resultado)
    }
}