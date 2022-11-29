package com.example.residenciaextintores

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Mantenimiento : AppCompatActivity() {
    var et_fechaI: EditText? = null
    var imageButton2: ImageButton? = null
    var fechas: DatePicker? = null
    var et_fechaF: EditText? = null
    var imageButton3: ImageButton? = null
    var fechasf: DatePicker? = null
    var et_id: EditText? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento)
        et_id = findViewById(R.id.et_id)
        et_fechaI = findViewById(R.id.et_fechaI)
        imageButton2 = findViewById(R.id.imageButton2)
        fechas = findViewById(R.id.fechas)
        et_fechaF = findViewById(R.id.et_fechaF)
        imageButton3 = findViewById(R.id.imageButton3)
        fechasf = findViewById(R.id.fechasf)

        et_fechaI?.setText(getFechaDate())
        fechas?.setOnDateChangedListener { fechas, ano, mes, dia ->
            et_fechaI?.setText(getFechaDate())
            fechas.visibility = View.GONE
        }

        et_fechaF?.setText(getFechaDatef())
        fechasf?.setOnDateChangedListener { fechasf, anos, mess, dias ->
            et_fechaF?.setText(getFechaDatef())
            fechasf.visibility = View.GONE
        }
    }
    fun getFechaDate(): String {
        var dia = fechas?.dayOfMonth.toString().padStart(length = 2, '0')
        var mes = (fechas!!.month + 1).toString().padStart(length = 2, '0')
        var ano = fechas?.year.toString().padStart(length = 4, '0')
        return ano + "-" + mes + "-" + dia
    }
    fun getFechaDatef(): String {
        var dias = fechasf?.dayOfMonth.toString().padStart(length = 2, '0')
        var mess = (fechasf!!.month + 1).toString().padStart(length = 2, '0')
        var anos = fechasf?.year.toString().padStart(length = 4, '0')
        return anos + "-" + mess + "-" + dias
    }
    fun muestraCalendario(view: View) {
        fechas?.visibility = View.VISIBLE
    }
    fun muestraCalendarioF(view: View) {
        fechasf?.visibility = View.VISIBLE
    }

    fun insertarM(view: View) {
        if (et_fechaI?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce fecha inicio.", Toast.LENGTH_LONG).show()
        } else if (et_fechaF?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce fecha final..", Toast.LENGTH_LONG).show()
        } else if (et_id?.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce id.", Toast.LENGTH_LONG).show()
        }
        else
        {
            val url = "https://proyectogexapp.000webhostapp.com/extintorbd/anadirmantenimiento.php"
            val queue = Volley.newRequestQueue(this)
            var resultPOST = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "Mantenimiento registrado exitosamete.", Toast.LENGTH_LONG)
                        .show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                        this,
                        "Error al registrar el mantenimiento.$error",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametro = HashMap<String, String>()

                    parametro.put("fecha_activo", et_fechaI?.text.toString())
                    parametro.put("fecha_vecimiento", et_fechaF?.text.toString())
                    parametro.put("IdExtintor", et_id?.text.toString())
                    return parametro
                }
            }
            queue.add(resultPOST)
        }
    }
}