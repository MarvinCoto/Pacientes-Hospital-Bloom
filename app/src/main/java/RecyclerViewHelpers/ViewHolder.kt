package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondepacientes_hospitalbloom.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)
    val imgEditar = view.findViewById<ImageView>(R.id.imgEditar)
    val imgBorrar = view.findViewById<ImageView>(R.id.imgBorrar)

}