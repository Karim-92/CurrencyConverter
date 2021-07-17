package com.karim.currencyconverter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karim.currencyconverter.databinding.ItemCurrencyBinding
import com.karim.currencyconverter.model.Rate

class RatesAdapter (val clickListener:RateItemListener): ListAdapter<Rate, RatesAdapter.ViewHolder>(diffUtil){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Rate, clickListener:RateItemListener) {
            binding.rateData = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Rate>() {

            override fun areItemsTheSame(
                oldItem: Rate,
                newItem: Rate
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: Rate,
                newItem: Rate
            ): Boolean =
                oldItem == newItem
        }
    }
}

class RateItemListener(val clickListener: (item: Rate) -> Unit) {
    fun onClick(item: Rate) = clickListener(item)
}