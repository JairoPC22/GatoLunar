package com.example.gatolunar

import SQLite
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.graphics.Color


class PruebaCom : AppCompatActivity() {
    var txtCodigo:EditText?=null
    var txtDescripcion:EditText?=null
    var txtPrecio:EditText?=null
    var txtBuscarPor:EditText?=null
    var tlProductos:TableLayout?=null
    var spBuscarPor:Spinner?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_com)

        txtCodigo=findViewById(R.id.txtCodigo)
        txtDescripcion=findViewById(R.id.txtDescripcion)
        txtPrecio=findViewById(R.id.txtPrecio)
        tlProductos=findViewById(R.id.tlProductos)
        txtBuscarPor=findViewById(R.id.txtBuscarPor)
        llenarTabla()
        spBuscarPor=findViewById(R.id.spBuscarPor)
        var listaCampos= arrayOf("Seleccione el campo a buscar","Codigo","Descripcion","Precio")
        var adaptador:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCampos)
        spBuscarPor?.adapter=adaptador
    }
    fun insertar(view:View){
        var con=SQLite(this,"tienda",null,1)
        var baseDatos=con.writableDatabase

        var codigo=txtCodigo?.text.toString()
        var descripcion=txtDescripcion?.text.toString()
        var precio=txtPrecio?.text.toString()

        if(codigo.isEmpty()==false && descripcion.isEmpty()==false && precio.isEmpty()==false){
            var registro=ContentValues()
            registro.put("codigo",codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio",precio)
            baseDatos.insert("productos",null,registro)
            txtCodigo?.setText("")
            txtDescripcion?.setText("")
            txtPrecio?.setText("")
            Toast.makeText(this,"Se insertado exitosamente",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Los campos deben tener texto",Toast.LENGTH_LONG).show()
        }
        baseDatos.close()
        llenarTabla()
    }
    fun eliminar(view:View){
        val con=SQLite(this,"tienda",null,1)
        val baseDatos=con.writableDatabase
        val codigo=txtCodigo?.text.toString()
        if(codigo.isEmpty()==false){
            val cant=baseDatos.delete("productos","codigo='"+codigo+"'",null)
            if(cant>0){
                Toast.makeText(this,"El producto fue eliminado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El producto no se encontro",Toast.LENGTH_LONG).show()
            }
            txtCodigo?.setText("")
            txtPrecio?.setText("")
            txtDescripcion?.setText("")
        }else{
            Toast.makeText(this,"El campo codigo debe tener texto",Toast.LENGTH_LONG).show()
        }
        llenarTabla()
    }
    fun editar(view:View){
        val con=SQLite(this,"tienda",null,1)
        val baseDados=con.writableDatabase

        val codigo=txtCodigo?.text.toString()
        val descripcion=txtDescripcion?.text.toString()
        val precio=txtPrecio?.text.toString()

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty() ){
            var registro=ContentValues()
            registro.put("codigo",codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio",precio)

            val cant=baseDados.update("productos",registro,"codigo='$codigo'",null)

            if(cant>0){
                Toast.makeText(this,"El registro se a editado exitosamente",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El registro no fue encontrado",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Los campos no deben estar vacios",Toast.LENGTH_LONG).show()
        }
        llenarTabla()
    }
    fun llenarTabla(){
        tlProductos?.removeAllViews()
        val con=SQLite(this,"tienda",null,1)
        val baseDatos=con.writableDatabase
        val buscarPor=txtBuscarPor?.text.toString()
        val listaBuscarPor=spBuscarPor?.selectedItem.toString()
        var sql=""
        if(!buscarPor.isEmpty()){
            if(listaBuscarPor=="Codigo"){
                sql="select codigo,descripcion,precio from productos where codigo='$buscarPor'"
            }
            else if(listaBuscarPor=="Descripcion"){
                sql="select codigo,descripcion,precio from productos where descripcion like'%$buscarPor%'"
            }
            else if(listaBuscarPor=="Precio"){
                sql="select codigo,descripcion,precio from productos where precio ='$buscarPor'"
            }else{
                sql="select codigo,descripcion,precio from productos"
            }
        }else{
            sql="select codigo,descripcion,precio from productos"
        }
        val fila=baseDatos.rawQuery(sql,null)
        fila.moveToFirst()
        do{
            val registro=LayoutInflater.from(this).inflate(R.layout.item_table_layout_pn,null,false)
            val tvCodigo=registro.findViewById<View>(R.id.tvCodigo) as TextView
            val tvDescripcion=registro.findViewById<View>(R.id.tvDescripcion) as TextView
            val tvPrecio=registro.findViewById<View>(R.id.tvPrecio) as TextView
            tvCodigo.setText(fila.getString(0))
            tvDescripcion.setText(fila.getString(1))
            tvPrecio.setText(fila.getString(2))
            tlProductos?.addView(registro)
        }while (fila.moveToNext())
    }
    fun clickRegistroProducto(view: View){
        resetColorRegistros()
        view.setBackgroundColor(Color.GRAY)
        val registro=view as TableRow
        val controlCodigo=registro.getChildAt(0) as TextView
        val codigo=controlCodigo.text.toString()
        val con=SQLite(this,"tienda",null,1)
        val baseDatos=con.writableDatabase
        if(!codigo.isEmpty()){
            val fila=baseDatos.rawQuery("select codigo,descripcion,precio from productos where codigo='$codigo'", null)
            if(fila.moveToFirst()){
                txtCodigo?.setText(fila.getString(0))
                txtDescripcion?.setText(fila.getString(1))
                txtPrecio?.setText(fila.getString(2))
            }else{
                txtCodigo?.setText("")
                txtDescripcion?.setText("")
                txtPrecio?.setText("")
                Toast.makeText(this, "No se ha encontrado ningun registro", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun resetColorRegistros(){
        for (i in 0 .. tlProductos!!.childCount){
            val registros=tlProductos?.getChildAt(i)
            registros?.setBackgroundColor(Color.WHITE)
        }
    }
    fun clickBotonBuscar(view: View){
        llenarTabla()
    }
}