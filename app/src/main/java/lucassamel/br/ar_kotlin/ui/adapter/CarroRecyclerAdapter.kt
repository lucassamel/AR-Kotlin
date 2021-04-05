package lucassamel.br.ar_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.list_carro_recycler.view.*
import lucassamel.br.ar_kotlin.R
import lucassamel.br.ar_kotlin.model.Modelo

class CarroRecyclerAdapter(private val carros: List<Modelo>,
    private val actionClick: (Modelo) -> Unit): RecyclerView.Adapter<CarroRecyclerAdapter.CarroViewHolder>(){

        class CarroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textModeloCarro : TextView = itemView.textViewModeloCarro
            val textMarcaCarro : TextView = itemView.textViewMarcaCarro
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroRecyclerAdapter
    .CarroViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.list_carro_recycler,
                parent, false
            )
        return CarroRecyclerAdapter.CarroViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarroRecyclerAdapter.CarroViewHolder, position:
    Int) {
        val carro = carros[position]
        holder.textModeloCarro.text = carro.nome
        holder.textMarcaCarro.text = carro.marca

        holder.itemView.btnDeleteCarro.setOnClickListener {
            deleteTrip(carro)
        }

        holder.itemView.setOnClickListener {
            actionClick(carro)
        }
    }

    override fun getItemCount(): Int = carros.size

    private fun deleteTrip(carro: Modelo): Task<Void> {
        val collection = FirebaseFirestore.getInstance().collection("carros")

        return collection
            .document(carro.nome!!)
            .delete()
    }
}