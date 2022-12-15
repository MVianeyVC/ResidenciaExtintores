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

class ActividadesProveedores : AppCompatActivity() {
    var lista_proveedores: TableLayout?=null
    var IdProveedor:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_proveedores)
        lista_proveedores=findViewById(R.id.lista_proveedor)
        cargaLista()
    }
    fun buscarprovedor(view: View){
        startActivity(intent)
        var et_id_busqueda:EditText=findViewById(R.id.et_id_busqueda)
        var intent=Intent(this,InformacionProveedor::class.java)
        intent.putExtra("IdProveedor",et_id_busqueda.text.toString())
        finish()
        startActivity(intent)
    }
    fun cargaLista(){
        lista_proveedores?.removeAllViews()
        var queue= Volley.newRequestQueue(this)
        var url="https://proyectogexapp.000webhostapp.com/extintorbd/registrosproveedores.php"
        var jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val registro= LayoutInflater.from(this).inflate(R.layout.lista_proveedores,null,false)
                        val IdProveedor=registro.findViewById<View>(R.id.IdProveedor) as TextView
                        val nombre=registro.findViewById<View>(R.id.nombre) as TextView
                        val Edit=registro.findViewById<View>(R.id.Edit)
                        val Delete=registro.findViewById<View>(R.id.Delete)
                        IdProveedor.text=jsonObject.getString("IdProveedor")
                        nombre.text=jsonObject.getString("nombre")
                        Edit.id=jsonObject.getString("IdProveedor").toInt()
                        Delete.id=jsonObject.getString("IdProveedor").toInt()
                        lista_proveedores?.addView(registro)
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
        val url="https://proyectogexapp.000webhostapp.com/extintorbd/borrarproveedor.php"
        val queue= Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this, "Proveedor borrado exitosamente.", Toast.LENGTH_LONG).show()
                cargaLista()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error al borrar proveedor. $error", Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("IdProveedor",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
    fun buscarEdit(view: View){
        IdProveedor=view.id.toString()
        var et_id_busque: TextView =findViewById(R.id.IdProveedor)
        var intent= Intent(this,InformacionProveedor::class.java)
        intent.putExtra("IdProveedor",IdProveedor.toString())
        finish()
        startActivity(intent)
    }
}