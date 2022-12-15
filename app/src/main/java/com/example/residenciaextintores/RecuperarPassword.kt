package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class RecuperarPassword : AppCompatActivity() {
    var ja: JSONArray? = null

    var contrasena: EditText?=null
    var confirmContr: EditText?=null
    var correoE: EditText?=null

    var Usuario:String?=null
    var Correo:String?=null

    var btnBuscar: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)

//        contrasena=findViewById(R.id.et_change_password)
//        confirmContr=findViewById(R.id.et_change_confirmPass)
        correoE=findViewById(R.id.et_change_correo) as EditText

        btnBuscar=findViewById(R.id.btn_change_buscar)

        Correo = intent.getStringExtra("correo").toString()

        btnBuscar!!.setOnClickListener { ConsultaCorreo("https://proyectogexapp.000webhostapp.com/extintorbd/verificarCorreoRegistrado.php?correo="
                + correoE!!.text.toString()) }
    }
    fun ConsultaCorreo(URL: String) {
        Log.i("url", "" + URL)
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, URL,
            { response ->
                try {
                    ja = JSONArray(response)
                    val consultaCorreo = ja!!.getString(0)
                    if (consultaCorreo == correoE?.getText().toString()) {

                        var et_id_busqueda:EditText=findViewById(R.id.et_change_correo)
                        Toast.makeText(applicationContext, "Correo Verficado", Toast.LENGTH_SHORT).show()
                        var intent= Intent(this,CambiarPassword::class.java)
                        intent.putExtra("correo",et_id_busqueda.text.toString())
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Verifique su correo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "El correo no existe en la base de datos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }) { }
        queue.add(stringRequest)
    }
}