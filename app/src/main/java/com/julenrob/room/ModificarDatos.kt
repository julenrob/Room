package com.julenrob.room

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.julenrob.room.databinding.ActivityModificarDatosBinding
import kotlinx.coroutines.launch

class ModificarDatos : AppCompatActivity() {
    lateinit var binding : ActivityModificarDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var idSelected = intent.extras!!.getString("idSelected")
        var selectedString = intent.extras!!.getString("selectedString")

        println("Los extras son: " + idSelected)
        binding.tvSeleccion.text = "A modificar el elemento con id: " + idSelected
        binding.tvSelectedString.text = selectedString

        binding.btnModifySelected.setOnClickListener {
            var newName = binding.etNewName.text.toString()
            var newEmail = binding.etNewEmail.text.toString()

            if (newName.isNotEmpty() && newEmail.isNotEmpty()){
                    lifecycleScope.launch {
                        try {
                            RoomApp.db.MisAmigosDAO().update(idSelected!!, newName, newEmail)
                            Toast.makeText(applicationContext, "Elemento modificado correctamente.", Toast.LENGTH_LONG).show()
                        } catch (e : Exception) {
                            Toast.makeText(applicationContext, "Error al modificar el elemento con id $idSelected", Toast.LENGTH_LONG).show()
                            Toast.makeText(applicationContext, "$e", Toast.LENGTH_LONG).show()
                        }

                    }
            } else {
                Toast.makeText(applicationContext, "Introduce los campos nombre y email.", Toast.LENGTH_LONG).show()
            }

        }

        binding.btnVolverDesdeModificar.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}