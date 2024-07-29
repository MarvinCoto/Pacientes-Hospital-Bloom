package RecyclerViewHelpers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondepacientes_hospitalbloom.R
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
                    eliminarRegistro(item.nombre, position)
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
    }

    //Función para actualizar recyclerView
    fun actualizarRecyclerView(nuevaLista: List<DataClassPacientes>) {
        Datos = nuevaLista
        notifyDataSetChanged()
    }

    //1- Crear la función de eliminar
    fun eliminarRegistro(nombrePaciente: String, posicion: Int) {
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








}