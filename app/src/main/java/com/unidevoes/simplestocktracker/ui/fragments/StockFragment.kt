package com.unidevoes.simplestocktracker.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.unidevoes.simplestocktracker.adapters.StockAdapter
import com.unidevoes.simplestocktracker.databinding.FragmentStockBinding
import com.unidevoes.simplestocktracker.ui.StockViewModel
import com.unidevoes.simplestocktracker.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockFragment : Fragment() {

    private var _binding: FragmentStockBinding? = null
    private val binding get() = _binding!!

    lateinit var stockAdapter: StockAdapter
    private val viewModel: StockViewModel by viewModels()
    val TAG = "StockFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater, container, false)

        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        viewModel.stocks.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { response ->
                        stockAdapter.differ.submitList(response.bestMatches)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message -> Log.e(TAG, message) }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }


    private fun hideProgressBar() {

    }

    private fun showProgressBar() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        stockAdapter = StockAdapter()
        binding.stockRecycler.apply {
            adapter = stockAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}