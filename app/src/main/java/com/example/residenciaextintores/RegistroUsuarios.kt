package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class RegistroUsuarios : AppCompatActivity() {
    var et_username: EditText? = null
    var et_password: EditText? = null
    var et_fullname: EditText? = null
    var et_email: EditText? = null
    var ja: JSONArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
    }
    fun insert(view: View) {

        if (et_fullname?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce nombre completo.", Toast.LENGTH_LONG).show()
        }
        else if (et_email?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce correo electrónico.", Toast.LENGTH_LONG).show()
        }
        else if (et_username?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce usuario.", Toast.LENGTH_LONG).show()
        }
        else if (et_password?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce contraseña.", Toast.LENGTH_LONG).show()
        }
        else
        {
            //Verificar que el correo este ya guardado en la BD ---FUNCIONA
            var URL="https://proyectogexapp.000webhostapp.com/extintorbd/verificarCorreoRegistrado.php?correo=" + et_email!!.text.toString()

            Log.i("url", "" + URL)
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET, URL,
                { response ->
                    try {
                        ja = JSONArray(response)
                        val consultaCorreo = ja!!.getString(0)
                        if (consultaCorreo == et_email?.getText().toString()) {

                            var et_id_busqueda:EditText=findViewById(R.id.et_email)
                            Toast.makeText(applicationContext, "El correo ya existe", Toast.LENGTH_SHORT).show()
                            var intent= Intent(this,RegistroUsuarios::class.java)
                            intent.putExtra("correo",et_id_busqueda.text.toString())
                            finish()
                            startActivity(intent)

                        } else {
                            Toast.makeText(applicationContext, "Llene los campos", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        //Insertar los datos--FUNCIONA

                        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/insercionusuarios.php"
                        val queue = Volley.newRequestQueue(this)
                        var resultPOST = object : StringRequest(
                            Request.Method.POST, url,
                            Response.Listener<String> { response ->

                                Toast.makeText(this, "Usuario registrado exitosamete.", Toast.LENGTH_LONG).show()
                                var intent = Intent(this,Menu::class.java)
                                finish()
                                startActivity(intent)

                            }, Response.ErrorListener { error ->
                                Toast.makeText(
                                    this,
                                    "Error al registrar el usuario.$error",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }) {
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
                }) { }
            queue.add(stringRequest)
        }
    }
    fun dirigir_login(view: View)
    {
        var intent = Intent(this,InicioSesion::class.java)
        finish()
        startActivity(intent)
    }
}