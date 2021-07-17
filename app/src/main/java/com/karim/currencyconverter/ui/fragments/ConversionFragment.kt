package com.karim.currencyconverter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import com.karim.currencyconverter.databinding.FragmentConversionBinding
import com.karim.currencyconverter.ui.viewmodels.RatesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversionFragment : Fragment() {
    private var binding: FragmentConversionBinding? = null
    private val ratesViewModel: RatesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentConversionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        var primaryText = binding!!.primaryvalueTv
        ratesViewModel.selectedRate.observe(viewLifecycleOwner, Observer {
            binding!!.secondaryTv.text = it.name
        })

        binding!!.secondaryvalueEt.doAfterTextChanged {
            if(binding!!.secondaryvalueEt.text.toString().isNotEmpty()) {
                ratesViewModel.convertValue(binding!!.secondaryvalueEt.text.toString().toDouble())
                binding!!.primaryvalueTv.text = ratesViewModel.calculatedValue.toString()
            }
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.conversionFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}