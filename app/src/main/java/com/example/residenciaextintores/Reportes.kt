package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Reportes : AppCompatActivity() {
    var lista_reportes: TableLayout?=null
    var IdExtintor:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)
        lista_reportes=findViewById(R.id.lista_reportes)
        cargaLista()
    }
    fun cargaLista(){
        lista_reportes?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/reportes.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_reportes,null,false)
                        val IdExtintor=registro.findViewById<View>(R.id.IdExtintor) as TextView
                        val folio=registro.findViewById<View>(R.id.folio) as TextView
                        val ubicacion=registro.findViewById<View>(R.id.et_ubicacion) as TextView
                        val Info=registro.findViewById<View>(R.id.btn_Informacion)
                        IdExtintor.text=jsonObject.getString("IdExtintor")
                        folio.text=jsonObject.getString("folio")
                        ubicacion.text=jsonObject.getString("ubicacion")
                        Info.id=jsonObject.getString("IdExtintor").toInt()
                        lista_reportes?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun inforepo(view:View){
        IdExtintor=view.id.toString()
        var et_id_busque:TextView=findViewById(R.id.IdExtintor)
        var intent= Intent(this,InformacionReporte::class.java)
        intent.putExtra("IdExtintor",IdExtintor.toString())
        finish()
        startActivity(intent)
    }
}