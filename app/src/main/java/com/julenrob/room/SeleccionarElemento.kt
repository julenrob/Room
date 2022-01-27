package com.julenrob.room

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.julenrob.room.databinding.ActivitySeleccionarElementoBinding
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.LongAsStringSerializer.serialize

class SeleccionarElemento : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivitySeleccionarElementoBinding
    //lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionarElementoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            adaptadorElementos()
        }


    }

    private suspend fun adaptadorElementos(){
        val listaElementos = RoomApp.db.MisAmigosDAO().getTodo()
        var adaptadorElementos = ArrayAdapter(this,
            R.layout.simple_spinner_dropdown_item, listaElementos)
        binding.spinner.adapter = adaptadorElementos
        binding.spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var selected : MisAmigos = parent!!.getItemAtPosition(position) as MisAmigos
        var selectedString : String = selected.id.toString() + " " + selected.nombre + " " + selected.email

        Toast.makeText(this, "Has seleccionado $selected", Toast.LENGTH_SHORT).show()

        var idSelected = selected.id.toString()

        binding.btnSeleccionar.setOnClickListener{
            var extra = intent.extras!!.getString("action")
            println("extra es : " + extra)
            var intent = Intent()
            if (extra == "remove"){
                intent = Intent(this, BorrarDatos::class.java)
            } else {
                intent = Intent(this, ModificarDatos::class.java)
            }
            intent.putExtra("selectedString", selectedString)
            intent.putExtra("idSelected", idSelected)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}