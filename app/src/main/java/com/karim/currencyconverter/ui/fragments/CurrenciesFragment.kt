package com.karim.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.karim.currencyconverter.R
import com.karim.currencyconverter.databinding.FragmentCurrenciesBinding
import com.karim.currencyconverter.ui.adapters.RateItemListener
import com.karim.currencyconverter.ui.adapters.RatesAdapter
import com.karim.currencyconverter.ui.viewmodels.RatesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private var binding: FragmentCurrenciesBinding? = null

    private val viewModel: RatesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCurrenciesBinding.inflate(inflater, container, false)
        Timber.d("Fragment onCreateView called.")
        binding = fragmentBinding
        binding!!.vm = viewModel
        recyclerView = binding!!.currenciesRv
        val adapter = RatesAdapter(RateItemListener { item ->
            viewModel.onItemClick(item)
            viewModel.navigationComplete()
        })
        binding!!.adapter = adapter
        viewModel.itemList.observe(viewLifecycleOwner, Observer
        {
            recyclerView.adapter = adapter
            adapter.submitList(it)
        })
        binding!!.networktoggle.setOnClickListener {
            viewModel.online=!viewModel.online
            viewModel.loadJsonFromAssets()
        }
        viewModel.navigateToConverstion.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.selectedRate.postValue(it)
                Timber.d("Selected Rate is: ${viewModel.selectedRate}")
                findNavController().navigate(R.id.action_currenciesFragment_to_conversionFragment)
            }
        })
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.currenciesFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}