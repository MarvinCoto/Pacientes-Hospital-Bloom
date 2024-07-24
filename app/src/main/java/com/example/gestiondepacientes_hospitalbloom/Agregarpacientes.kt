package com.example.gestiondepacientes_hospitalbloom

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Agregarpacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregarpacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtEdad)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombrepaciente = findViewById<EditText>(R.id.txtNombrepaciente)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)
        val txtedad = findViewById<EditText>(R.id.txtedad)
        val txtFechanac = findViewById<EditText>(R.id.txtFechanac)
        val txtEnfermedad = findViewById<EditText>(R.id.txtEnfermedad)
        val txtMedicamento = findViewById<EditText>(R.id.txtMedicamento)
        val txtHoramedicamento = findViewById<EditText>(R.id.txtHoramedicamento)
        val txtNumCama = findViewById<EditText>(R.id.txtNumCama)
        val txtNumHabitacion = findViewById<EditText>(R.id.txtNumHabitacion)
        val btnAgregarpacientes = findViewById<Button>(R.id.btnAgregarpacientes)
        val btnverlistadopacientes = findViewById<Button>(R.id.btnverlistadopacientes)
        val icregresar = findViewById<ImageView>(R.id.icregresar)

        btnAgregarpacientes.setOnClickListener {
            val Nombre = txtNombrepaciente.text.toString()
            val Apellido = txtApellido.text.toString()
            val Edad = txtedad.text.toString()
            val FechaNac = txtFechanac.text.toString()
            val Enfermedad = txtEnfermedad.text.toString()
            val Medicamento = txtMedicamento.text.toString()
            val HoraMedicamento = txtHoramedicamento.text.toString()
            val NumCama = txtNumCama.text.toString()
            val NumHabitacion = txtNumHabitacion.text.toString()

            var hayErrores = false

            if (Nombre.isEmpty()) {
                txtNombrepaciente.error = "El nombre es obligatorio"
                hayErrores = true
            } else {
                txtNombrepaciente.error = null
            }

            if (Apellido.isEmpty()) {
                txtApellido.error = "El apellido es obligatorio"
                hayErrores = true
            } else {
                txtApellido.error = null
            }

            if (!Edad.matches(Regex("[0-9]"))) {
                txtedad.error = "La edad debe ser de tipo número, es obligatoria"
                hayErrores = true
            } else {
                txtedad.error = null
            }

            if (FechaNac.isEmpty()) {
                txtFechanac.error = "La fecha de nacimiento es obligatoria"
                hayErrores = true
            } else {
                txtFechanac.error = null
            }
            

                if (Enfermedad.isEmpty()) {
                    txtEnfermedad.error = "La enfermedad es obligatoria"
                    hayErrores = true
                } else {
                    txtEnfermedad.error = null
                }

                if (Medicamento.isEmpty()) {
                    txtMedicamento.error = "El medicamento es obligatorio"
                    hayErrores = true
                } else {
                    txtMedicamento.error = null
                }

                if (HoraMedicamento.isEmpty()) {
                    txtHoramedicamento.error = "La hora del medicamento es obligatoria"
                    hayErrores = true
                } else {
                    txtHoramedicamento.error = null
                }

                if (!NumCama.matches(Regex("[0-9]"))) {
                    txtNumCama.error = "El número de cama debe ser de tipo numérico, es obligatorio"
                    hayErrores = true
                } else {
                    txtNumCama.error = null
                }

                if (!NumHabitacion.matches(Regex("[0-9]"))) {
                    txtNumHabitacion.error =
                        "El número de habitación debe ser de tipo numérico, es obligatorio"
                    hayErrores = true
                } else {
                    txtNumHabitacion.error = null
                }


            }

            btnverlistadopacientes.setOnClickListener {

            }

            icregresar.setOnClickListener {
                val pantallabienvenida = Intent(this, MainActivity::class.java)
                startActivity(pantallabienvenida)
            }
        }
    }
}