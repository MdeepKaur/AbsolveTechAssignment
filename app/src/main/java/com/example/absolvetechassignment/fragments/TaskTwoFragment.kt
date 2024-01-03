package com.example.absolvetechassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.absolvetechassignment.databinding.TaskOneFragmentBinding
import com.example.absolvetechassignment.databinding.TaskTwoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskTwoFragment : Fragment() {

    private var _binding: TaskTwoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskTwoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}