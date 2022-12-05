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

class RegistroUsuarios : AppCompatActivity() {
    var et_username: EditText? = null
    var et_password: EditText? = null
    var et_fullname: EditText? = null
    var et_email: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
    }
    fun insert(view: View) {
        if (et_fullname?.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Introduce nombre completo.", Toast.LENGTH_LONG).show()
        }
        else if (et_email?.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Introduce correo electrónico.", Toast.LENGTH_LONG).show()
        }
        else if (et_username?.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Introduce usuario.", Toast.LENGTH_LONG).show()
        }
        else if (et_password?.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Introduce contraseña.", Toast.LENGTH_LONG).show()
        }
        else
        {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/insercionusuarios.php"
            val queue = Volley.newRequestQueue(this)
            var resultPOST = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String>
                {
                        response ->
                    Toast.makeText(this,"Usuario registrado exitosamete.", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,Menu::class.java)
                        startActivity(intent)
                },
                Response.ErrorListener
                {
                        error ->
                    Toast.makeText(this,"Error al registrar el usuario.$error", Toast.LENGTH_LONG).show()
                })
            {
                override fun getParams(): MutableMap<String, String>? {
                    val parametro = HashMap<String, String>()
                    parametro.put("usuario", et_username?.text.toString())
                    parametro.put("contraseña", et_password?.text.toString())
                    parametro.put("nombre_completo", et_fullname?.text.toString())
                    parametro.put("correo", et_email?.text.toString())
                    return parametro
                }
            }
            queue.add(resultPOST)
        }
    }
    fun dirigir_login(view: View)
    {
        var intent = Intent(this,InicioSesion::class.java)
        finish()
        startActivity(intent)
    }
}