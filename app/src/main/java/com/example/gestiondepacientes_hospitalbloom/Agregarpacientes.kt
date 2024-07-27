package com.example.gestiondepacientes_hospitalbloom

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.DataClassCamas
import modelo.DataClassHabitaciones
import modelo.DataClassEnfermedades
import modelo.DataClassMedicamentos
import java.util.Calendar
import java.util.UUID

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
        val spEnfermedad = findViewById<Spinner>(R.id.spEnfermedad)
        val spMedicamentos = findViewById<Spinner>(R.id.spMedicamentos)
        val txtHoramedicamento = findViewById<EditText>(R.id.txtHoramedicamento)
        val spNumCama = findViewById<Spinner>(R.id.spNumCama)
        val spNumHabitacion = findViewById<Spinner>(R.id.spNumHabitacion)
        val btnAgregarpacientes = findViewById<Button>(R.id.btnAgregarpacientes)
        val btnverlistadopacientes = findViewById<Button>(R.id.btnverlistadopacientes)
        val icregresar = findViewById<ImageView>(R.id.icregresar)

        fun Obtenerenfermedades (): List<DataClassEnfermedades>{
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM ENFERMEDADES")!!

            val listadoEnfermedades = mutableListOf<DataClassEnfermedades>()

            while (resultSet.next()){
                val uuid = resultSet.getString("UUIDEnfermedades")
                val Enfermedad = resultSet.getString("Enfermedad")
                val Enfermedadcompleta = DataClassEnfermedades(uuid, Enfermedad)
                listadoEnfermedades.add(Enfermedadcompleta)
            }
            return listadoEnfermedades
        }

        CoroutineScope(Dispatchers.IO).launch {
            val listadoEnfermedades = Obtenerenfermedades()
            val nombreenfermedad = listadoEnfermedades.map { it.Enfermedad }

            withContext(Dispatchers.Main)  {
                val miAdaptadorenfermedad = ArrayAdapter(this@Agregarpacientes, android.R.layout.simple_spinner_dropdown_item, nombreenfermedad)
                spEnfermedad.adapter = miAdaptadorenfermedad
            }
        }

        fun Obtenermedicamentos (): List<DataClassMedicamentos>{
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM MEDICAMENTOS")!!

            val listadoMedicamentos = mutableListOf<DataClassMedicamentos>()

            while (resultSet.next()){
                val uuid = resultSet.getString("UUIDMedicamentos")
                val Medicamento = resultSet.getString("Medicamento")
                val Medicamentocompleto = DataClassMedicamentos(uuid, Medicamento)
                listadoMedicamentos.add(Medicamentocompleto)
            }
            return listadoMedicamentos
        }

        CoroutineScope(Dispatchers.IO).launch {
            val listadodemedicamentos = Obtenermedicamentos()
            val nombremedicamento = listadodemedicamentos.map { it.Medicamento }

            withContext(Dispatchers.Main)  {
                val miAdaptadormedicamento = ArrayAdapter(this@Agregarpacientes, android.R.layout.simple_spinner_dropdown_item, nombremedicamento)
                spMedicamentos.adapter = miAdaptadormedicamento
            }
        }

        fun ObtenerHabitaciones (): List<DataClassHabitaciones> {

            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM HABITACIONES")!!

            val listadoHabitaciones = mutableListOf<DataClassHabitaciones>()

            while (resultSet.next()){
                val uuid = resultSet.getString("UUIDHabitacion")
                val habitacion = resultSet.getString("N_Habitacion")
                val habitacionCompleta = DataClassHabitaciones(uuid, habitacion)
                listadoHabitaciones.add(habitacionCompleta)
            }
            return listadoHabitaciones
        }

        CoroutineScope(Dispatchers.IO).launch {
            val listadohabitaciones = ObtenerHabitaciones()
            val habitacion = listadohabitaciones.map { it.N_Habitacion }

            withContext(Dispatchers.Main) {
                val miAdaptadorHabitacion = ArrayAdapter(this@Agregarpacientes, android.R.layout.simple_spinner_dropdown_item, habitacion)
                spNumHabitacion.adapter = miAdaptadorHabitacion
            }
        }

        fun ObtenerCamas (): List<DataClassCamas> {

            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM CAMAS")!!

            val listadoCamas = mutableListOf<DataClassCamas>()

            while (resultSet.next()){
                val uuid = resultSet.getString("UUIDCama")
                val cama = resultSet.getString("N_Cama")
                val camaCompleta = DataClassCamas(uuid, cama)
                listadoCamas.add(camaCompleta)
            }
            return listadoCamas
        }

        CoroutineScope(Dispatchers.IO).launch {
            val listadoCamas = ObtenerCamas()
            val cama = listadoCamas.map { it.N_Cama }

            withContext(Dispatchers.Main) {
                val miAdaptadorCama = ArrayAdapter(this@Agregarpacientes, android.R.layout.simple_spinner_dropdown_item, cama)
                spNumCama.adapter = miAdaptadorCama
            }
        }

        txtFechanac.setOnClickListener {
            val calendario = java.util.Calendar.getInstance()
            val anio = calendario.get(java.util.Calendar.YEAR)
            val mes = calendario.get(java.util.Calendar.MONTH)
            val dia = calendario.get(java.util.Calendar.DAY_OF_MONTH)


            val fechaMinima = java.util.Calendar.getInstance()
            fechaMinima.set(anio - 100, mes, dia)

            val datePickerDialog = DatePickerDialog(this@Agregarpacientes,
                { view, anioSeleccionado, mesSeleccionado, diaSeleccionado ->
                    val fechaSeleccionada = "$diaSeleccionado/${mesSeleccionado + 1}/$anioSeleccionado"
                    txtFechanac.setText(fechaSeleccionada)
                },
                anio, mes, dia
            )

            datePickerDialog.datePicker.minDate = fechaMinima.timeInMillis

            datePickerDialog.datePicker.maxDate = calendario.timeInMillis

            datePickerDialog.show()
        }

        txtHoramedicamento.setOnClickListener {

                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                // Crear el TimePickerDialog
                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, selectedHour, selectedMinute ->
                        val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                        txtHoramedicamento.setText(time)
                    },
                    hour,
                    minute,
                    true
                )

                timePickerDialog.show()
        }

        
        btnAgregarpacientes.setOnClickListener {
            val Nombre = txtNombrepaciente.text.toString()
            val Apellido = txtApellido.text.toString()
            val Edad = txtedad.text.toString()
            val FechaNac = txtFechanac.text.toString()
            val Horamedicamento = txtFechanac.text.toString()


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
                txtedad.error = "La edad es obligatoria"
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

            if (Horamedicamento.isEmpty()) {
                txtHoramedicamento.error = "La hora del medicamento es obligatoria"
                hayErrores = true
            } else {
                txtHoramedicamento.error = null
            }

            
            CoroutineScope(Dispatchers.IO).launch {

                val objConexion = ClaseConexion().cadenaConexion()

                val enfermedad = Obtenerenfermedades()
                val medicamento = Obtenermedicamentos()
                val habitacion = ObtenerHabitaciones()
                val camas = ObtenerCamas()


                val addPaciente = objConexion?.prepareStatement("insert into PACIENTES (UUIDPaciente, UUIDMedicamentos, UUIDEnfermedades, UUIDHabitacion, UUIDCama, Nombre, Apellido, Edad, Fecha_nacimiento, Hora_medicamento) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")!!
                addPaciente.setString(1, UUID.randomUUID().toString())
                addPaciente.setString(2, medicamento[spMedicamentos.selectedItemPosition].UUIDMedicamentos)
                addPaciente.setString(3, enfermedad[spEnfermedad.selectedItemPosition].UUIDEnfermedades)
                addPaciente.setString(4, habitacion[spNumHabitacion.selectedItemPosition].UUIDHabitacion)
                addPaciente.setString(5, camas[spNumCama.selectedItemPosition].UUIDCama)
                addPaciente.setString(6, txtNombrepaciente.text.toString())
                addPaciente.setString(7, txtApellido.text.toString())
                addPaciente.setString(8, txtedad.text.toString())
                addPaciente.setString(9, txtFechanac.text.toString())
                addPaciente.setString(10, txtHoramedicamento.text.toString())

                addPaciente.executeUpdate()

                withContext(Dispatchers.Main){
                    txtNombrepaciente.setText("")
                    txtApellido.setText("")
                    txtedad.setText("")
                    txtFechanac.setText("")
                    txtHoramedicamento.setText("")
                    Toast.makeText(this@Agregarpacientes, "¡Se ha agregado el paciente!", Toast.LENGTH_SHORT).show()
                }
                

            }


            }

            btnverlistadopacientes.setOnClickListener {
                val pantallalistadopacientes = Intent(this, activity_listado_pacientes::class.java)
                startActivity(pantallalistadopacientes)
            }

            icregresar.setOnClickListener {
                val pantallabienvenida = Intent(this, MainActivity::class.java)
                startActivity(pantallabienvenida)
            }
        }
    }
