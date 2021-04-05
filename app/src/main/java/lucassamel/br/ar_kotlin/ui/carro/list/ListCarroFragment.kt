package lucassamel.br.ar_kotlin.ui.carro.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_carro_fragment.*
import lucassamel.br.ar_kotlin.R
import lucassamel.br.ar_kotlin.dao.carro.CarroDaoFirestore
import lucassamel.br.ar_kotlin.model.AppUtil
import lucassamel.br.ar_kotlin.model.Modelo
import lucassamel.br.ar_kotlin.ui.adapter.CarroRecyclerAdapter

class ListCarroFragment : Fragment() {

    private lateinit var viewModel: ListCarroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listCarros = ListCarroViewModelFactory(CarroDaoFirestore())

        viewModel = ViewModelProvider(this, listCarros).get(ListCarroViewModel::class.java)

        viewModel.carros.observe(viewLifecycleOwner) {
            setupListViewCarros(it)
        }

        viewModel.atualizarQuantidade()


        return inflater.inflate(R.layout.list_carro_fragment, container, false)
    }

    private fun setupListViewCarros(carros: List<Modelo>) {
        listCarros.adapter = CarroRecyclerAdapter(carros) {
            AppUtil.modeloSelecionado = it
        }
        listCarros.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListCarroViewModel::class.java)

        fabNovoCarro.setOnClickListener {
            findNavController().navigate((R.id.action_listCarroFragment_to_marcaFragment))
        }
    }

}