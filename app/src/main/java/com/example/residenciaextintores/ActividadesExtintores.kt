package com.example.residenciaextintores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class ActividadesExtintores : AppCompatActivity() {
    var lista_extintores:TableLayout?=null
    var IdExtintor:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_extintores)
        lista_extintores=findViewById(R.id.lista_extintores)
        cargaLista()
    }
    fun cargaLista(){
        lista_extintores?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/registros.php"
        var jsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_registros_extintores,null,false)
                        val IdExtintor=registro.findViewById<View>(R.id.IdExtintor) as TextView
                        val folio=registro.findViewById<View>(R.id.folio) as TextView
                        val Edit=registro.findViewById<View>(R.id.Edit)
                        val Delete=registro.findViewById<View>(R.id.Delete)
                        IdExtintor.text=jsonObject.getString("IdExtintor")
                        folio.text=jsonObject.getString("folio")
                        Edit.id=jsonObject.getString("IdExtintor").toInt()
                        Delete.id=jsonObject.getString("IdExtintor").toInt()
                        lista_extintores?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaBorrar(view: View){
        val url="https://proyectogexapp.000webhostapp.com/extintorbd/borrar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this, "Extintor borrado exitosamente.", Toast.LENGTH_LONG).show()
                cargaLista()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al borrar extintor. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("IdExtintor",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
    fun buscar(view:View){
        var et_id_busqueda:EditText=findViewById(R.id.et_id_busqueda)
        var intent=Intent(this,InformacionExtintor::class.java)
        intent.putExtra("IdExtintor",et_id_busqueda.toString())
        finish()
        startActivity(intent)
    }
    fun buscarEdit(view:View){
        IdExtintor=view.id.toString()
        var et_id_busque:TextView=findViewById(R.id.IdExtintor)
        var intent=Intent(this,InformacionExtintor::class.java)
        intent.putExtra("IdExtintor",IdExtintor.toString())
        finish()
        startActivity(intent)
    }
}