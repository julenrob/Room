package com.julenrob.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.julenrob.room.RoomApp.Companion.db
import com.julenrob.room.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() && binding.etEmail.text.isNotBlank()){
                addAmigo(MisAmigos(nombre = binding.etNombre.text.toString(),
                                    email = binding.etEmail.text.toString())
                        )
            }
        }
        binding.btConsultar.setOnClickListener{
            lifecycleScope.launch {
                val todos = db.MisAmigosDAO().getTodo()
                var i = 0;
                var guardado = "";
                while(i < todos.size){
                    guardado += todos[i].nombre +" "+todos[i].email+"\n"
                    i++
                }
                binding.tvConsulta.text = guardado
            }
        }
        binding.btIrABorrar.setOnClickListener{
            val intent = Intent(this, SeleccionarElemento::class.java)
            intent.putExtra("action", "remove")
            startActivity(intent)
        }
        binding.btIrAModificar.setOnClickListener{
            val intent = Intent(this, SeleccionarElemento::class.java)
            intent.putExtra("action", "modify")
            startActivity(intent)
        }
    }

    private fun addAmigo(miAmigo:MisAmigos){
        lifecycleScope.launch {
            db.MisAmigosDAO().insertar(miAmigo)
        }
    }
}