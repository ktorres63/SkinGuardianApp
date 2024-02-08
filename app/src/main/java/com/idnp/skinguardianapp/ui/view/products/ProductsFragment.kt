package com.idnp.skinguardianapp.ui.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.databinding.FragmentProductsBinding
import com.idnp.skinguardianapp.ui.adapter.ProductsAdapter

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Listas de títulos y descripciones
        val titlesList = mutableListOf<String>()
        val descriptionsList = mutableListOf<String>()
        for (i in 1..10) {
            titlesList.add(getString(resources.getIdentifier("titulo$i", "string", requireContext().packageName)))
            descriptionsList.add(getString(resources.getIdentifier("texto$i", "string", requireContext().packageName)))
        }

        // Set up RecyclerView
        val recyclerView = binding.rvProducts
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProductsAdapter(requireContext(), titlesList, descriptionsList)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

