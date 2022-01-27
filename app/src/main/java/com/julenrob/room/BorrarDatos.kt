package com.julenrob.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.julenrob.room.databinding.ActivityBorrarDatosBinding
import kotlinx.coroutines.launch

class BorrarDatos : AppCompatActivity() {
    lateinit var binding : ActivityBorrarDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var idSelected = intent.extras!!.getString("idSelected")
        var selectedString = intent.extras!!.getString("selectedString")

        println("Los extras son: " + idSelected)
        binding.tvSeleccionado.text = "A borrar el elemento con id: " + idSelected
        binding.tvSelectedStringRemove.text = selectedString

        lifecycleScope.launch {
            try {
                RoomApp.db.MisAmigosDAO().delete(idSelected!!)
                Toast.makeText(applicationContext, "Elemento borrado correctamente.", Toast.LENGTH_LONG).show()
            } catch (e : Exception) {
                Toast.makeText(applicationContext, "Error al borrar el elemento con id $idSelected", Toast.LENGTH_LONG).show()
                Toast.makeText(applicationContext, "$e", Toast.LENGTH_LONG).show()
            }


        }

        binding.btnVolverDesdeBorrar.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}