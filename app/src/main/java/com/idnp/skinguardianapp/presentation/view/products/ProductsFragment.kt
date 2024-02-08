package com.idnp.skinguardianapp.presentation.view.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.databinding.FragmentProductsBinding
import com.idnp.skinguardianapp.databinding.FragmentRoutinesBinding
import com.idnp.skinguardianapp.domain.model.ProductsProvider.Companion.productList
import com.idnp.skinguardianapp.presentation.adapters.ProductsAdapter
import com.idnp.skinguardianapp.presentation.adapters.RoutinesAdapter


class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var  rvProducts: RecyclerView
    private lateinit var productAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initUI()
    }
    private fun initComponent(){
        rvProducts = binding.rvProducts
    }

    private fun initUI(){
        productAdapter = ProductsAdapter(productList)
        rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.adapter = productAdapter

    }

}