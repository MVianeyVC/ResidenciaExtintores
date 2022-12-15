package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class LecturaMantenimiento : AppCompatActivity() {
    var lista_basico_mantenimiento: TableLayout?=null
    var IdMantenimiento:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectura_mantenimiento)
        lista_basico_mantenimiento=findViewById(R.id.lista_basico_mantenimiento)
        cargaLista()
    }
    fun buscar(view: View){
        var et_id_busqueda: EditText =findViewById(R.id.et_id_busqueda)
        var intent= Intent(this,InformacionLecturaMantenimiento::class.java)
        intent.putExtra("IdMantenimiento",et_id_busqueda.text.toString())
        finish()
        startActivity(intent)
    }
    fun cargaLista(){
        lista_basico_mantenimiento?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/registrosmantenimiento.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_basico_mantenimiento,null,false)
                        val IdMantenimiento=registro.findViewById<View>(R.id.IdMantenimiento) as TextView
                        val fecha_activo=registro.findViewById<View>(R.id.fecha_activo) as TextView
                        val fecha_vecimiento=registro.findViewById<View>(R.id.fecha_vecimiento) as TextView
                        val IdExtintor=registro.findViewById<View>(R.id.IdExtintor) as TextView
                        val Inf=registro.findViewById<View>(R.id.btn_Informacion)
                        IdMantenimiento.text=jsonObject.getString("IdMantenimiento")
                        fecha_activo.text=jsonObject.getString("fecha_activo")
                        fecha_vecimiento.text=jsonObject.getString("fecha_vecimiento")
                        IdExtintor.text=jsonObject.getString("IdExtintor")
                        Inf.id=jsonObject.getString("IdMantenimiento").toInt()
                        lista_basico_mantenimiento?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun buscarEdit(view: View){
        IdMantenimiento=view.id.toString()
        var et_id_busque: TextView =findViewById(R.id.IdMantenimiento)
        var intent= Intent(this,InformacionLecturaMantenimiento::class.java)
        intent.putExtra("IdMantenimiento",IdMantenimiento.toString())
        finish()
        startActivity(intent)
    }
}