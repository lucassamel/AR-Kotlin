package lucassamel.br.ar_kotlin.ui.carro.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lucassamel.br.ar_kotlin.R

class ListCarroFragment : Fragment() {

    companion object {
        fun newInstance() = ListCarroFragment()
    }

    private lateinit var viewModel: ListCarroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_carro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListCarroViewModel::class.java)
        // TODO: Use the ViewModel
    }

}