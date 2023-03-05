package com.unidevoes.simplestocktracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unidevoes.simplestocktracker.databinding.ItemStockPreviewBinding
import com.unidevoes.simplestocktracker.models.Stock

class StockAdapter : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    inner class StockViewHolder(val binding: ItemStockPreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ItemStockPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = differ.currentList[position]
        holder.binding.apply {
            stockName.text = stock.name
            stockCode.text = stock.symbol
            root.setOnClickListener {
                onItemClickListener?.let { it(stock) }
            }
        }
    }

    private var onItemClickListener: ((Stock) -> Unit)? = null

    fun setOnItemClickListener(listener: (Stock) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}