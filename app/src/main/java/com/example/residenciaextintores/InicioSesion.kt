package com.example.residenciaextintores

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException


class  InicioSesion : AppCompatActivity() {

    var etUsuario: EditText? = null
    var etContra: EditText? = null
    var btnIngresar: Button? = null
    var ja: JSONArray? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)
        etUsuario = findViewById(R.id.et_username) as EditText
        etContra = findViewById(R.id.et_password) as EditText
        btnIngresar = findViewById(R.id.btn_logiin) as Button

        btnIngresar!!.setOnClickListener { ConsultaPass("https://proyectogexapp.000webhostapp.com/extintorbd/consultarusuario.php?usuario="
                + etUsuario!!.text.toString()) }

    }

    private fun ConsultaPass(URL: String) {
        Log.i("url", "" + URL)
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, URL,
            { response ->
                try {
                    ja = JSONArray(response)
                    val contra = ja!!.getString(0)
                    if (contra == etContra?.getText().toString()) {
                        Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@InicioSesion, Menu::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Verifique su contraseña",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "El usuario no existe en la base de datos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }) { }
        queue.add(stringRequest)
    }
    ///Funcion para pasar de Login a registro
    fun signUp(view: View){
        var intent = Intent(this,RegistroUsuarios::class.java)
        startActivity(intent)
    }


}