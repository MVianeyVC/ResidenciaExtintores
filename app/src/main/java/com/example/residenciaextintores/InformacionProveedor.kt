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

class InformacionProveedor : AppCompatActivity() {
    var et_id_proveedor:EditText? = null
    var et_nombre:EditText? = null
    var et_direccion:EditText? = null
    var et_telefono:EditText? = null
    var et_rfc:EditText? = null
    var et_municipio_estado:EditText? = null
    var et_cp:EditText? = null
    var et_id_universidad:EditText? = null
    var et_id_extintor:EditText? = null
    var IdProveedor:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_proveedor)
        et_id_proveedor=findViewById(R.id.et_id_proveedor)
        et_nombre=findViewById(R.id.et_nombre)
        et_direccion=findViewById(R.id.et_direccion)
        et_telefono=findViewById(R.id.et_telefono)
        et_rfc=findViewById(R.id.et_rfc)
        et_municipio_estado=findViewById(R.id.et_municipio_estado)
        et_cp=findViewById(R.id.et_cp)
        et_id_universidad=findViewById(R.id.et_id_universidad)
        et_id_extintor=findViewById(R.id.et_id_extintor)

        IdProveedor = intent.getStringExtra("IdProveedor").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registroproveedor.php?IdProveedor=$IdProveedor"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                et_id_proveedor?.setText(response.getString("IdProveedor"))
                et_nombre?.setText(response.getString("nombre"))
                et_direccion?.setText(response.getString("direccion"))
                et_telefono?.setText(response.getString("telefono"))
                et_rfc?.setText(response.getString("RFC"))
                et_municipio_estado?.setText(response.getString("municipio_estado"))
                et_cp?.setText(response.getString("cp"))
                et_id_universidad?.setText(response.getString("IdUniversidad"))
                et_id_extintor?.setText(response.getString("IdExtintor"))
            },
            { error ->
                Toast.makeText(this, "No existe proveedor. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesProveedores::class.java)
                finish()
                startActivity(intent)
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun borrar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/borrarproveedor.php"
        val queue = Volley.newRequestQueue(this)
        var resultado = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this, "Proveedor borrado exitosamente.", Toast.LENGTH_LONG).show()
            },
            { error ->
                Toast.makeText(this, "Error al borrar proveedor. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("IdProveedor", IdProveedor!!)
                return parametros
            }
        }
        queue.add(resultado)
    }
    fun editar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editarproveedor.php"
        val queue = Volley.newRequestQueue(this)
        val resultado = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(this, "Proveedor editado exitosamente.", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesProveedores::class.java)
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this, "Error al editar proveedor. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("IdProveedor", IdProveedor!!)
                parametros.put("nombre", et_nombre?.text.toString())
                parametros.put("direccion", et_direccion?.text.toString())
                parametros.put("telefono", et_telefono?.text.toString())
                parametros.put("RFC", et_rfc?.text.toString())
                parametros.put("municipio_estado", et_municipio_estado?.text.toString())
                parametros.put("cp", et_cp?.text.toString())
                parametros.put("IdUniversidad", et_id_universidad?.text.toString())
                parametros.put("IdExtintor", et_id_extintor?.text.toString())
                return parametros
            }
        }
        queue.add(resultado)
    }
}