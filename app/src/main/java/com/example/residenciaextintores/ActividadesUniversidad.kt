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

class ActividadesUniversidad : AppCompatActivity() {
    var lista_universidades: TableLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_universidad)
        lista_universidades=findViewById(R.id.lista_universidades)
        cargaLista()
    }
    fun buscar(view: View){
        var et_id_busqueda: EditText =findViewById(R.id.et_id_busqueda)
        var intent= Intent(this,InformacionUniversidad::class.java)
        intent.putExtra("IdUniversidad",et_id_busqueda.text.toString())
        finish()
        startActivity(intent)
    }
    fun cargaLista(){
        lista_universidades?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/registrosuniversidad.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_universidades,null,false)
                        val IdUniversidad=registro.findViewById<View>(R.id.IdUniversidad) as TextView
                        val nombre=registro.findViewById<View>(R.id.nombre) as TextView
                        val Edit=registro.findViewById<View>(R.id.Edit)
                        val Delete=registro.findViewById<View>(R.id.Delete)
                        IdUniversidad.text=jsonObject.getString("IdUniversidad")
                        nombre.text=jsonObject.getString("nombre")
                        Edit.id=jsonObject.getString("IdUniversidad").toInt()
                        Delete.id=jsonObject.getString("IdUniversidad").toInt()
                        lista_universidades?.addView(registro)
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
        val url="https://proyectogexapp.000webhostapp.com/extintorbd/borraruniversidad.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this, "Universidad borrado exitosamente.", Toast.LENGTH_LONG).show()
                cargaLista()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al borrar universidad. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("IdUniversidad",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
    fun buscarEdit(view: View){
        var et_id_busque: TextView =findViewById(R.id.IdUniversidad)
        var intent= Intent(this,InformacionUniversidad::class.java)
        intent.putExtra("IdUniversidad",et_id_busque.text.toString())
        finish()
        startActivity(intent)
    }
}