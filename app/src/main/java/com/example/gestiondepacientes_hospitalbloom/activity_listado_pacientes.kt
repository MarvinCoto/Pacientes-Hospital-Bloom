package com.example.gestiondepacientes_hospitalbloom

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_listado_pacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val icregresaragregarpacientes = findViewById<ImageView>(R.id.icregresaragregarpacientes)


        icregresaragregarpacientes.setOnClickListener{
            val pantallaagregarpacientes = Intent(this, Agregarpacientes::class.java)
            startActivity(pantallaagregarpacientes)
        }
    }
}