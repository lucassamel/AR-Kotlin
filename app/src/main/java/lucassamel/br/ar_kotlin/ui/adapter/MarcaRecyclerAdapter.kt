package lucassamel.br.ar_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.marca_recycler.view.*
import lucassamel.br.ar_kotlin.R
import lucassamel.br.ar_kotlin.model.Marca
import lucassamel.br.ar_kotlin.model.ProjectMarca

class MarcaRecyclerAdapter(
    private val marcas: List<ProjectMarca>,
    private val actionClick: (Marca) -> Unit
): RecyclerView.Adapter<MarcaRecyclerAdapter.MarcaViewHolder>() {

    class MarcaViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView){
            val textMarca : TextView = itemView.textViewBrandMarca
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        MarcaViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.marca_recycler,
                    parent, false
                )
            return MarcaRecyclerAdapter.MarcaViewHolder(view)
        }


        override fun onBindViewHolder(holder: MarcaRecyclerAdapter.MarcaViewHolder, position:
        Int) {
            val marca = marcas[position]
            holder.textMarca.text = marca.nome

            holder.itemView.btnSelectMarca.setOnClickListener {
                //selectMarca(marca)
            }

//            holder.itemView.setOnClickListener {
//                actionClick(marca)
//            }
        }


    override fun getItemCount(): Int = marcas.size

//    private fun selectMarca(marca: Marca): Task<Void> {
//
//        return
//    }
}