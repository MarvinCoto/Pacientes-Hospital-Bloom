package RecyclerViewHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondepacientes_hospitalbloom.R
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
    }


}