package RecyclerViewHelpers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondepacientes_hospitalbloom.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import modelo.DataClassPacientes

class Adaptador (var Datos: List<DataClassPacientes>): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Uno el recyclerView con mi card
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_pacientes, parent, false)
        return ViewHolder(vista)
    }

    //Devolver la cantidad de datos que se muestran
    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Controlar mi card
        val item = Datos[position]
        holder.txtNombreCard.text = item.nombre

        //Configurar el icono para eliminar datos
        holder.imgBorrar.setOnClickListener {

            //Crear alerta de confirmación para borrar
            val context = holder.txtNombreCard.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Estás seguro de que deseas eliminar al paciente?")

            //Botones de mi alerta
            builder.setPositiveButton("Si") {
                    dialog, wich ->
                    eliminarPaciente(item.nombre, position)
            }

            builder.setNegativeButton("No") {
                    dialog, wich ->
                //Si doy click en "no" se cerrara la alerta
                dialog.dismiss()
            }

            //Para que se muestre mi alerta
            val dialog = builder.create()
            dialog.show()

        }

        holder.imgEditar.setOnClickListener {
            val context = holder.itemView.context
            val builder = android.app.AlertDialog.Builder(context)

            builder.setTitle("Editar Paciente")

            val txtNuevoMedicamento = EditText(context).apply {
                setText(item.uuidMedicamentos)
            }

            val txtNuevaEnfermedad = EditText(context).apply {
                setText(item.uuidEnfermedades)
            }

            val txtNuevaHabitacion = EditText(context).apply {
                setText(item.uuidHabitacon)
            }

            val txtNuevaCama = EditText(context).apply {
                setText(item.uuidCama)
            }

            val txtNuevoNombre = EditText(context).apply {
                setText(item.nombre)
            }

            val txtNuevoApellido = EditText(context).apply {
                setText(item.apellido)
            }

            val txtNuevaEdad = EditText(context).apply {
                setText(item.edad.toString())
            }

            val txtNuevaFecha = EditText(context).apply {
                setText(item.fecha_nacimiento)
            }

            val txtNuevaHora = EditText(context).apply {
                setText(item.hora_medicamento)
            }

            val layout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                addView(txtNuevoMedicamento)
                addView(txtNuevaEnfermedad)
                addView(txtNuevaHabitacion)
                addView(txtNuevaCama)
                addView(txtNuevoNombre)
                addView(txtNuevoApellido)
                addView(txtNuevaEdad)
                addView(txtNuevaFecha)
                addView(txtNuevaHora)
            }

            builder.setView(layout)

            builder.setPositiveButton("Guardar Cambios") {
                    dialog, wich -> editarPaciente(txtNuevoMedicamento.text.toString(), txtNuevaEnfermedad.text.toString(), txtNuevaHabitacion.text.toString(), txtNuevaCama.text.toString(), txtNuevoNombre.text.toString(), txtNuevoApellido.text.toString(), txtNuevaEdad.text.toString().toInt(), txtNuevaFecha.text.toString(), txtNuevaHora.text.toString(), item.uuidPaciente)
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, wich -> dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

    }

    //Función para actualizar recyclerView
    fun actualizarRecyclerView(nuevaLista: List<DataClassPacientes>) {
        Datos = nuevaLista
        notifyDataSetChanged()
    }

    //1- Crear la función de eliminar
    fun eliminarPaciente(nombrePaciente: String, posicion: Int) {
        //Se notifica al adaptador
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        //Quitar de la base de datos
        GlobalScope.launch(Dispatchers.IO) {
            //Dos pasos para eliminar de la base

            //1- Crear un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            //2- Creamos una variable que contenga un PrepareStatement
            val deleteProducto = objConexion?.prepareStatement("DELETE PACIENTES WHERE Nombre = ?")!!
            deleteProducto.setString(1, nombrePaciente)
            deleteProducto.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }

        //Notificamos los cambios para refrescar la lista
        Datos = listaDatos.toList()
        //Quitamos los datos de la lista
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    //Funcion para editar datos
    fun editarPaciente (uuidMedicamentos: String, uuidEnfermedades: String, uuidHabitacon: String, uuidCama: String, nombre: String, apellido: String, edad: Int, fecha_nacimiento: String, hora_medicamento: String, uuidPaciente: String,) {
        //Creomos una corrutina
        GlobalScope.launch(Dispatchers.IO) {

        //Creamos el objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

        //Creamos la variable con el prepare Statement
            val updatePaciente = objConexion?.prepareStatement("UPDATE PACIENTES SET UUIDMedicamentos = ?, UUIDEnfermedades = ?, UUIDHabitacion = ?, UUIDCama = ?, Nombre = ?, Apellido = ?, Edad = ?, Fecha_nacimiento = ?, Hora_medicamento = ? WHERE UUIDPaciente = ?")!!
            updatePaciente.setString(1, uuidMedicamentos)
            updatePaciente.setString(2, uuidEnfermedades)
            updatePaciente.setString(3, uuidHabitacon)
            updatePaciente.setString(4, uuidCama)
            updatePaciente.setString(5, nombre)
            updatePaciente.setString(6, apellido)
            updatePaciente.setInt(7, edad)
            updatePaciente.setString(8, fecha_nacimiento)
            updatePaciente.setString(9, hora_medicamento)
            updatePaciente.setString(10, uuidPaciente)
            updatePaciente.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }
    }


}