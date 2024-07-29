package com.example.gestiondepacientes_hospitalbloom

import RecyclerViewHelpers.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.DataClassPacientes

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

        //Mando a llamar a mi recyclerView
        val rcvPacientes = findViewById<RecyclerView>(R.id.rcvPacientes)

        //Agrego un layout a mi recyclerView
        rcvPacientes.layoutManager = LinearLayoutManager(this)

        //Funcion para mostrar mis datos

        fun obtenerPacientes(): List<DataClassPacientes> {

            //1- Creo el objeto de la clase conexión
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Creo mi statement
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM PACIENTES")!!

            val listadoPacientes = mutableListOf<DataClassPacientes>()

            while (resultSet.next()) {
                val uuidPaciente = resultSet.getString("UUIDPaciente")
                val uuidMedicamentos = resultSet.getString("UUIDMedicamentos")
                val uuidEnfermedades = resultSet.getString("UUIDEnfermedades")
                val uuidHabitacion = resultSet.getString("UUIDHabitacion")
                val uuidCama = resultSet.getString("UUIDCama")
                val nombre = resultSet.getString("Nombre")
                val apellido = resultSet.getString("Apellido")
                val edad = resultSet.getInt("Edad")
                val fecha_nacimiento = resultSet.getString("Fecha_nacimiento")
                val hora_medicamento = resultSet.getString("Hora_medicamento")

                val valoresJuntos = DataClassPacientes(uuidPaciente, uuidMedicamentos, uuidEnfermedades, uuidHabitacion, uuidCama, nombre, apellido, edad, fecha_nacimiento, hora_medicamento)

                listadoPacientes.add(valoresJuntos)
            }
            return listadoPacientes
        }

        //Asignar el adaptador a mi recyclerView
        CoroutineScope(Dispatchers.IO).launch {
            val pacientesDB = obtenerPacientes()

            withContext(Dispatchers.Main){
                val adapter = Adaptador(pacientesDB)
                rcvPacientes.adapter = adapter
            }
        }

        icregresaragregarpacientes.setOnClickListener{
            val pantallaagregarpacientes = Intent(this, Agregarpacientes::class.java)
            startActivity(pantallaagregarpacientes)
        }
    }
}