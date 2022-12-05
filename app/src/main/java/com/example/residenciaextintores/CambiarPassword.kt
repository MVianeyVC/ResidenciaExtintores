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

class CambiarPassword : AppCompatActivity() {
    var contrasena: EditText?=null
    var confirmContrasena: EditText?=null
    var Correo:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_password)
        contrasena=findViewById(R.id.et_change_password)
        confirmContrasena=findViewById(R.id.et_change_confirmPass)

        Correo = intent.getStringExtra("correo").toString()
    }
    fun guardar(view: View) {
        val value1: String = contrasena?.getText().toString().trim()
        val value2: String = confirmContrasena?.getText().toString().trim()
        if (value1.isEmpty() && value2.isEmpty()) {
            Toast.makeText(this, "Llene los campos", Toast.LENGTH_LONG).show()
            return
        }
        else if (!value1.contentEquals(value2)) {
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG).show()
            return
        }else
        {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editarContrasena.php"
            val queue = Volley.newRequestQueue(this)
            val resultado = object : StringRequest(
                Request.Method.POST, url, Response.Listener {
                    Toast.makeText(this, "Contraseña guardada", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,InicioSesion::class.java)
                    startActivity(intent)
                },
                { error ->
                    Toast.makeText(this, "Error al guardar contraseña. $error", Toast.LENGTH_LONG).show()
                }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val parametros = HashMap<String, String>()
                    parametros.put("correo", Correo!!)
                    parametros.put("contraseña", contrasena?.text.toString())
                    return parametros
                }
            }
            queue.add(resultado)
        }
    }
}