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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import java.net.URL

class  InicioSesion : AppCompatActivity() {
    var etUsuario: EditText? = null
    var etContra: EditText? = null
    var btnIngresar: Button? = null
    var ja: JSONArray? = null
    var jaa: JSONArray? = null
    var jaa2: JSONArray? = null
    var Usuario:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)
        etUsuario = findViewById(R.id.et_username) as EditText
        etContra = findViewById(R.id.et_password) as EditText
        btnIngresar = findViewById(R.id.btn_logiin) as Button
        Usuario = intent.getStringExtra("usuario").toString()
        btnIngresar!!.setOnClickListener{
            ConsultaPass("https://proyectogexapp.000webhostapp.com/extintorbd/consultarusuario.php?usuario=" + etUsuario!!.text.toString())
        }
    }
    private fun ConsultaPass(URL: String) {
        Log.i("url", "" + URL)
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, URL,
            {
                    response ->
                try {
                    ja = JSONArray(response)
                    val contra = ja!!.getString(0)
                    if (contra == etContra?.getText().toString()) {
                        menu("https://proyectogexapp.000webhostapp.com/extintorbd/consultarrolusuario.php?usuario="
                                + etUsuario!!.text.toString())
                    }
                    else {
                        Toast.makeText(applicationContext, "Verifique su contraseña", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "El usuario no existe en la base de datos", Toast.LENGTH_LONG).show()
                }
            }) { }
        queue.add(stringRequest)
    }
    fun signUp(view: View) {
        var intent = Intent(this,RegistroUsuarios::class.java)
        finish()
        startActivity(intent)
    }
    fun recuperarPass(view: View) {
        var intent = Intent(this,RecuperarPassword::class.java)
        startActivity(intent)
    }
    fun menu(URL: String) {
        Log.i("url", "" + URL)
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, URL,
            { response ->
                jaa = JSONArray(response)
                val role = jaa!!.getString(0)
                if (role == "Basico") {
                    //Toast.makeText(applicationContext, "Bienvenido "+ etUsuario!!.text.toString(), Toast.LENGTH_SHORT).show()
                   // Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                    //var intent= Intent(this,MenuBasico::class.java)
                    //finish()
                    //startActivity(intent)
                    menurol("https://proyectogexapp.000webhostapp.com/extintorbd/consultarpermisousuario.php?usuario="
                            + etUsuario!!.text.toString())
                }
                else if(role == "Admin") {
                    /*Toast.makeText(applicationContext, "Bienvenido "+ etUsuario!!.text.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuBonito::class.java)
                    finish()
                    startActivity(intent)*/
                    menurol("https://proyectogexapp.000webhostapp.com/extintorbd/consultarpermisousuario.php?usuario="
                            + etUsuario!!.text.toString())
                }
            }
            ,
            { error ->
                Toast.makeText(this, "No tiene rol asignado. $error", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(stringRequest)
    }
    fun menurol(URL: String) {
            Log.i("url", "" + URL)
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, URL,
                { response ->
                    jaa2 = JSONArray(response)
                    val permiso = jaa2!!.getString(0)
                    if (permiso == "CRUD") {
                        Toast.makeText(applicationContext, "Bienvenido "+ etUsuario!!.text.toString(), Toast.LENGTH_SHORT).show()
                        // Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                        var intent= Intent(this,MenuBonito::class.java)
                        finish()
                        startActivity(intent)
                    }
                    else if(permiso == "Read") {
                        Toast.makeText(applicationContext, "Bienvenido "+ etUsuario!!.text.toString(), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MenuBasico::class.java)
                        finish()
                        startActivity(intent)
                    }
                }
                ,
                { error ->
                    Toast.makeText(this, "No tiene permiso asignado. $error", Toast.LENGTH_LONG).show()
                }
            )
            queue.add(stringRequest)
        }
}