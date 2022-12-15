package com.example.residenciaextintores

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class InformacionMantenimiento : AppCompatActivity() {
    /* Declaracion de los identificadores segun el tipo del xml en null. */
    var et_idman: EditText? = null
    var et_fechaI: EditText? = null
    var et_fechaF: EditText? = null
    var et_id: EditText? = null
    var IdMantenimiento:String? = null
    var fechas: DatePicker? = null
    var fechasf: DatePicker? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_mantenimiento)
        et_idman = findViewById(R.id.et_idman)
        et_fechaI = findViewById(R.id.et_fechaI)
        et_fechaF = findViewById(R.id.et_fechaF)
        et_id = findViewById(R.id.et_id)
        fechas = findViewById(R.id.fechas)
        fechasf = findViewById(R.id.fechasf)

        et_idman?.setText("")
        et_id?.setText("")
        et_fechaI?.setText("")
        et_fechaF?.setText("")

        IdMantenimiento = intent.getStringExtra("IdMantenimiento").toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/registromantenimiento.php?IdMantenimiento=$IdMantenimiento"
        val jsonObjectRequest = JsonObjectRequest( Request.Method.GET, url, null,
            { response ->
                fechas?.setOnDateChangedListener { fechas, ano, mes, dia ->
                    et_fechaI?.setText(getFechaDate())
                    fechas.visibility = View.GONE
                }
                et_fechaF?.setText(getFechaDatef())
                fechasf?.setOnDateChangedListener { fechasf, anos, mess, dias ->
                    et_fechaF?.setText(getFechaDatef())
                    fechasf.visibility = View.GONE
                }
                et_idman?.setText(response.getString("IdMantenimiento"))
                et_fechaI?.setText(response.getString("fecha_activo"))
                et_fechaF?.setText(response.getString("fecha_vecimiento"))
                et_id?.setText(response.getString("IdExtintor"))
            },
            {
                    error ->
                Toast.makeText(this, "No existen mantenimietos. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesMantenimiento::class.java)
                finish()
                startActivity(intent)
            })
        queue.add(jsonObjectRequest)
    }
    /* Funcion para borrar mantenimiento. */
    fun borrar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/borrarmantenimiento.php"
        val queue = Volley.newRequestQueue(this)
        var resultado = object : StringRequest(Request.Method.POST, url, Response.Listener
        { response ->
                Toast.makeText(this, "Mantenimiento borrado exitosamente.", Toast.LENGTH_LONG).show()
            var intent = Intent(this,ActividadesMantenimiento::class.java)
            finish()
            startActivity(intent)
            },
            {
                    error ->
                Toast.makeText(this, "Error al borrar mantenimiento. $error", Toast.LENGTH_LONG).show()
            })
        {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros.put("IdMantenimiento", IdMantenimiento!!)
                return parametros
            }
        }
        queue.add(resultado)
    }
    /* Funcion para editar mantenimiento. */
    fun editar(view: View) {
        val url = "https://proyectogexapp.000webhostapp.com/extintorbd/editarmantenimiento.php"
        val queue = Volley.newRequestQueue(this)
        val resultado = object : StringRequest(Request.Method.POST, url,
            Response.Listener {
                Toast.makeText(this, "Mantenimiento editado exitosamente.", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesMantenimiento::class.java)
                finish()
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this, "Error al editar antenimiento. $error", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ActividadesMantenimiento::class.java)
                finish()
                startActivity(intent)
            })
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros.put("IdMantenimiento", IdMantenimiento!!)
                parametros.put("fecha_activo", et_fechaI?.text.toString())
                parametros.put("fecha_vecimiento", et_fechaF?.text.toString())
                parametros.put("IdExtintor", et_id?.text.toString())
                return parametros
            }
        }
        queue.add(resultado)
    }
    /* Muestra el calendario de la fecha incial en que se registra el mantenimiento. */
    fun muestraCalendario(view: View) {
        fechas?.visibility = View.VISIBLE
    }
    /* Muestra el calendario de la fecha en que vence el mantenimiento. */
    fun muestraCalendarioF(view: View) {
        fechasf?.visibility = View.VISIBLE
    }
    /* Para dar formato a la fecha de inicio mantenimiento YYYY-MM-DD */
    fun getFechaDate(): String {
        var dia = fechas?.dayOfMonth.toString().padStart(length = 2, '0')
        var mes = (fechas!!.month + 1).toString().padStart(length = 2, '0')
        var ano = fechas?.year.toString().padStart(length = 4, '0')
        return ano + "-" + mes + "-" + dia
    }
    /* Para dar formato a la fecha de en que vence mantenimiento YYYY-MM-DD */
    fun getFechaDatef(): String {
        var dias = fechasf?.dayOfMonth.toString().padStart(length = 2, '0')
        var mess = (fechasf!!.month + 1).toString().padStart(length = 2, '0')
        var anos = fechasf?.year.toString().padStart(length = 4, '0')
        return anos + "-" + mess + "-" + dias
    }
}