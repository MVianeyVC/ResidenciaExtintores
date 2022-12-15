package com.example.residenciaextintores

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class InformacioUsuarios : AppCompatActivity() {
    var et_id_usuario: EditText? = null
    var et_fullname: EditText? = null
    var et_email: EditText? = null
    var et_username: EditText? = null
    var et_password: EditText? = null
    //var spinner: EditText? = null
    var et_permiso: EditText? =null
    var et_rol: EditText? = null
    var IdUsuario:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacio_usuarios)
        et_id_usuario=findViewById(R.id.et_id_usuario)
        et_fullname=findViewById(R.id.et_fullname)
        et_email=findViewById(R.id.et_email)
        et_username=findViewById(R.id.et_username)
        et_password=findViewById(R.id.et_password)
        et_rol=findViewById(R.id.et_rol)
        et_permiso=findViewById(R.id.et_permiso)

        IdUsuario = intent.getStringExtra("IdUsuario").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registrousuarios.php?IdUsuario=$IdUsuario"
        //val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registrouniversidad.php?"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                et_id_usuario?.setText(response.getString("IdUsuario"))
                et_username?.setText(response.getString("usuario"))
                et_fullname?.setText(response.getString("nombre_completo"))
                et_password?.setText(response.getString("contraseña"))
                et_email?.setText(response.getString("correo"))
                et_rol?.setText(response.getString("rol"))
                et_permiso?.setText(response.getString("permiso"))

            },
            { error ->
                Toast.makeText(this, "No existe usuario. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesProveedores::class.java)
                finish()
                startActivity(intent)
            }
        )
        queue.add(jsonObjectRequest)
    }
   /* fun borrar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/borrar.php"
        val queue = Volley.newRequestQueue(this)
        var resultado = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this, "Extintor borrado exitosamente.", Toast.LENGTH_LONG).show()
            },
            { error ->
                Toast.makeText(this, "Error al borrar extintor. $error", Toast.LENGTH_LONG).show()
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
    }*/
    fun editar(view: View) {

       if (et_rol?.getText().toString().isEmpty()) {
           Toast.makeText(this, "Introduce rol de usuario.", Toast.LENGTH_LONG).show()
       }
       else if (et_permiso?.getText().toString().isEmpty()) {
           Toast.makeText(this, "Introduce permiso de usuario.", Toast.LENGTH_LONG).show()
       }
       else if (et_rol?.getText().toString() != "Admin" && et_rol?.getText().toString() != "Basico") {
           Toast.makeText(this, "Introduce Admin ó Basico.", Toast.LENGTH_LONG).show()
       }
       else if (et_permiso?.getText().toString() != "CRUD" && et_permiso?.getText().toString() != "Read") {
           Toast.makeText(this, "Introduce CRUD ó Read.", Toast.LENGTH_LONG).show()
       }
       else {
           val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editarrolusuario.php"
           val queue = Volley.newRequestQueue(this)
           val resultado = object : StringRequest(Request.Method.POST, url,
               Response.Listener {
                   Toast.makeText(this, "Usuario editado exitosamente.", Toast.LENGTH_LONG).show()
                   var intent = Intent(this, ActividadesUsuarios::class.java)
                   finish()
                   startActivity(intent)
               },
               { error ->
                   //Toast.makeText(this, "Error al editar rol de usuario. $error", Toast.LENGTH_LONG).show()
               }
           ) {
               override fun getParams(): MutableMap<String, String> {
                   val parametros = HashMap<String, String>()
                   parametros.put("IdUsuario", IdUsuario!!)
                   parametros.put("rol", et_rol?.text.toString())
                   return parametros
               }
           }
           val url2 = "https://proyectogexapp.000webhostapp.com/extintorbd/editarpermisousuario.php"
           val queue2 = Volley.newRequestQueue(this)
           val resultado2 = object : StringRequest(Request.Method.POST, url2,
               Response.Listener {
                   /* Toast.makeText(this, "Permiso de usuario editado exitosamente.", Toast.LENGTH_LONG).show()
               var intent = Intent(this,ActividadesUsuarios::class.java)
               finish()
               startActivity(intent)*/
               },
               { error ->
                   Toast.makeText(this, "Error al editar usuario. $error", Toast.LENGTH_LONG).show()
               }
           ) {
               override fun getParams(): MutableMap<String, String> {
                   val parametros = HashMap<String, String>()
                   parametros.put("IdUsuario", IdUsuario!!)
                   parametros.put("permiso", et_permiso?.text.toString())
                   return parametros
               }
           }
           queue.add(resultado)
           queue2.add(resultado2)
       }
   }
}