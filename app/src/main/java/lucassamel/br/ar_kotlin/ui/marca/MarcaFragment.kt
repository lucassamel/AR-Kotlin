package lucassamel.br.ar_kotlin.ui.marca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import lucassamel.br.ar_kotlin.R

class MarcaFragment : Fragment() {

    private lateinit var viewModel: MarcaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)
            .get(MarcaViewModel::class.java)

        val root = inflater.inflate(R.layout.marca_fragment, container, false)

        val listViewPorjectMarca: ListView = root.findViewById(R.id.listViewMarca)

        viewModel.marcas.observe(viewLifecycleOwner, Observer {
            listViewPorjectMarca
                .adapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    it
                )
            listViewPorjectMarca.setOnItemClickListener { parent, view, position, id ->
                val bundle = bundleOf(
                    "codigo" to it[position].codigo
                )
                findNavController().navigate(R.id.action_marcaFragment_to_formCarroFragment, bundle)
            }
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_LONG
                ).show()
        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)

    }


}