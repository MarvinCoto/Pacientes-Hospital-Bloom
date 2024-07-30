package com.example.gestiondepacientes_hospitalbloom

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_detalle_pacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mandar a llamar a todos los elementos de la vista
        val txtNombrePaciente = findViewById<TextView>(R.id.txtNombrePaciente)
        val txtApellidoPaciente = findViewById<TextView>(R.id.txtApellidoPaciente)
        val txtFechaNacimiento = findViewById<TextView>(R.id.txtFechaNacimiento)
        val txtEdadPaciente = findViewById<TextView>(R.id.txtEdadPaciente)
        val txtHabitacionPaciente = findViewById<TextView>(R.id.txtHabitacion)
        val txtCamaPaciente= findViewById<TextView>(R.id.txtCama)
        val txtEnfermedadPaciente = findViewById<TextView>(R.id.txtEnfermedad)
        val txtMedicinaPaciente = findViewById<TextView>(R.id.txtMedicina)
        val txtHoraPaciente = findViewById<TextView>(R.id.txtHora)
        val icRegresar = findViewById<ImageView>(R.id.icRegresar)

        //Recibir los valores
        val nombreRecibido = intent.getStringExtra("Nombre")
        val apellidoRecibido = intent.getStringExtra("Apellido")
        val fechaRecibida = intent.getStringExtra("Fecha_nacimiento")
        val edadRecibida = intent.getIntExtra("Edad", 0)
        val habitacionRecibida = intent.getStringExtra("UUIDHabitacion")
        val camaRecibida = intent.getStringExtra("UUIDCama")
        val enfermedadRecibida = intent.getStringExtra("UUIDEnfermedades")
        val medicamentoRecibido = intent.getStringExtra("UUIDMedicamentos")
        val horaRecibida = intent.getStringExtra("Hora_medicamento")


        //Asigno los valores a mis TextViews
        txtNombrePaciente.text = nombreRecibido
        txtApellidoPaciente.text = apellidoRecibido
        txtFechaNacimiento.text = fechaRecibida
        txtEdadPaciente.text = edadRecibida.toString()
        txtHabitacionPaciente.text = habitacionRecibida
        txtCamaPaciente.text = camaRecibida
        txtEnfermedadPaciente.text = enfermedadRecibida
        txtMedicinaPaciente.text = medicamentoRecibido
        txtHoraPaciente.text = horaRecibida


        icRegresar.setOnClickListener {
            val pantallaPacientes = Intent(this, activity_listado_pacientes::class.java)
            startActivity(pantallaPacientes)
        }


    }
}