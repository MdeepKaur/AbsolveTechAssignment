package com.example.absolvetechassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.absolvetechassignment.R
import com.example.absolvetechassignment.databinding.FirstFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FirstFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirstFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addClickListeners()
    }

    private fun addClickListeners() {

        binding.btTask1.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_taskOneFragment)
        }

        binding.btTask2.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_taskTwoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}