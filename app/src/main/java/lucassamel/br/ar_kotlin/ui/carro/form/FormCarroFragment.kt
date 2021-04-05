package lucassamel.br.ar_kotlin.ui.carro.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.form_carro_fragment.*
import lucassamel.br.ar_kotlin.R
import lucassamel.br.ar_kotlin.dao.carro.CarroDaoFirestore
import lucassamel.br.ar_kotlin.model.Modelo
import lucassamel.br.ar_kotlin.model.ProjectModelo

class FormCarroFragment : Fragment() {

    private lateinit var viewModel: FormCarroViewModel
    private lateinit var viewModelFactory: FormCarroViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val codigo = arguments?.getLong("codigo")
        val modelo = arguments?.get("modelo").toString()
        if (codigo == null)
            findNavController().popBackStack()

        viewModelFactory = FormCarroViewModelFactory(codigo!!,CarroDaoFirestore())
//        Toast.makeText(
//            requireContext(),
//            "$codigo",
//            Toast.LENGTH_LONG
//        ).show()

        val listaModelos = mutableListOf<String>()


        viewModel = ViewModelProvider(this, viewModelFactory).get(FormCarroViewModel::class.java)

        val root = inflater.inflate(R.layout.form_carro_fragment, container, false)


        viewModel.modelos.observe(viewLifecycleOwner, Observer {

            textViewMarcaModelo.text = modelo

            it.modelos.forEach {
                listaModelos.add(it.nome!!)
            }

            Log.i("API", "${viewModel.modelos.value}")

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                listaModelos,
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerModelos.adapter = adapter
            spinnerModelos.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val modeloSelected = it.modelos[position].nome
                        viewModel.modeloSelecionado(modeloSelected)
                    }
                }
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast
                    .makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()
        })

        return root
    }

//    fun spinnerItems(): LiveData<ProjectModelo>{
//        viewModel.modelos
//
//        return nomeModelo
//    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormCarroViewModel::class.java)


        btnCadastrarCarro.setOnClickListener {
            val modelo = spinnerModelos.selectedItem.toString()
            val marca = textViewMarcaModelo.text.toString()

            viewModel.insertCarro(modelo,marca)

            findNavController().navigate(R.id.action_formCarroFragment_to_listCarroFragment)
        }

    }

}