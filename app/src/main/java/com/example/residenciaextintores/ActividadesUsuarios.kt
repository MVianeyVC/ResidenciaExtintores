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

class ActividadesUsuarios : AppCompatActivity() {
    var lista_usuarios: TableLayout?=null
    var IdUsuario:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_usuarios)
        lista_usuarios=findViewById(R.id.lista_usuarios)
        cargaLista()
    }
    fun cargaLista(){
        lista_usuarios?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/registrosusuarios.php"
        var jsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_usuarios,null,false)
                        val IdUsuario=registro.findViewById<View>(R.id.IdUsuario) as TextView
                        val usuario=registro.findViewById<View>(R.id.usuario) as TextView
                        val rol=registro.findViewById<View>(R.id.rol) as TextView
                        val Edit=registro.findViewById<View>(R.id.Edit)
                        //val Delete=registro.findViewById<View>(R.id.Delete)
                        IdUsuario.text=jsonObject.getString("IdUsuario")
                        usuario.text=jsonObject.getString("usuario")
                        rol.text=jsonObject.getString("rol")
                        Edit.id=jsonObject.getString("IdUsuario").toInt()
                        //Delete.id=jsonObject.getString("IdUsuario").toInt()
                        lista_usuarios?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }, { error ->
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun clickTablaBorrarUsuarios(view: View){
        val url="https://proyectogexapp.000webhostapp.com/extintorbd/borrarusuarios.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this, "Usuario borrado exitosamente.", Toast.LENGTH_LONG).show()
                cargaLista()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al borrar usuario. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("IdUsuario",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
    fun buscarUsuarios(view: View){
        var et_id_busqueda: EditText =findViewById(R.id.et_id_busqueda)
        var intent= Intent(this,InformacioUsuarios::class.java)
        intent.putExtra("IdUsuario",et_id_busqueda.text.toString())
        finish()
        startActivity(intent)
    }
    fun buscarEditUsuarios(view: View){
        IdUsuario=view.id.toString()
        //var et_id_busque: TextView =findViewById(R.id.IdUniversidad)
        var intent= Intent(this,InformacioUsuarios::class.java)
        intent.putExtra("IdUsuario",IdUsuario.toString())
        finish()
        startActivity(intent)
    }
}