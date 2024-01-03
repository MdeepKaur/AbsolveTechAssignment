package com.example.absolvetechassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.absolvetechassignment.R
import com.example.absolvetechassignment.databinding.ItemProductBinding
import com.example.absolvetechassignment.databinding.TaskOneFragmentBinding
import com.example.absolvetechassignment.models.Product
import com.example.absolvetechassignment.utils.GenericAdapter
import com.example.absolvetechassignment.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskOneFragment : Fragment() {

    private var _binding: TaskOneFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<TaskOneViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskOneFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllProducts()
        addAdapter()
        addObservers()
    }

    private fun addAdapter() {
        viewModel.productAdapter = object : GenericAdapter<Product>(R.layout.item_product) {
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                ItemProductBinding.bind(holder.itemView).let { rvBinding ->
                    getItemAt(position)?.let { data ->
                        rvBinding.tvName.text = data.category
                        rvBinding.tvDescription.text = data.description
                        val discount = String.format("%.2f", data.price * 0.2).replace(".00", "").toDouble()
                        val price = String.format("%.2f", data.price).replace(".00", "").toDouble()
                        rvBinding.tvPrice.text = "$${price - discount}"
                        rvBinding.tvPriceStrike.text = "$$price"
                        Glide.with(context)
                            .load(data.image)
                            .into(rvBinding.imgProduct)
                    }
                }
            }
        }

        binding.rvProducts.adapter = viewModel.productAdapter
    }

    private fun addObservers() {
        viewModel.allProduct.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.e("NETWORK_RESULT", "SUCCESS")
                    Log.e("RESULT", it.data.toString())
                    binding.progressBar.visibility=View.GONE
                    viewModel.productAdapter.submitList(it.data)
                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility=View.GONE
                    Log.e("NETWORK_RESULT", "ERROR")
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.visibility=View.VISIBLE
                    Log.e("NETWORK_RESULT", "LOADING")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}