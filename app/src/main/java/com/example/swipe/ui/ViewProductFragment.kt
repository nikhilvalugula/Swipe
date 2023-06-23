package com.example.swipe.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.swipe.R
import com.example.swipe.databinding.FragmentViewProductBinding
import com.example.swipe.db.ProductDataItem
import com.example.swipe.networkclient.RetrofitClient
import com.example.swipe.repo.ViewProductsRepo
import com.example.swipe.util.Coroutines
import com.example.swipe.viewModels.ViewProductViewModel
import com.example.swipe.viewModels.ViewProductViewModelFactory


class ViewProductFragment : Fragment() {

    private lateinit var binding: FragmentViewProductBinding

    private var adapter = ProductAdapter()

    private var searchAadapter = SearchAdapter()

    private lateinit var model: ViewProductViewModel

    private val myAPi = RetrofitClient.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewProductBinding.inflate(LayoutInflater.from(this.context))
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(
            this,
            ViewProductViewModelFactory(ViewProductsRepo(myAPi))
        )[ViewProductViewModel::class.java]
        setUpUi()
        binding.buttonAdd.setOnClickListener {
            val action = ViewProductFragmentDirections.actionAddProduct()
            Navigation.findNavController(it).navigate(action)
        }
        setHasOptionsMenu(true)
    }

    private fun setUpUi() {
        Coroutines.main {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonAdd.visibility = View.INVISIBLE
            model.fetchProducts().observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.INVISIBLE
                binding.buttonAdd.visibility = View.VISIBLE
                setUpRecyclerView(it)
            }
        }
    }

    private fun setUpSearchRecyclerView(productDataItem: List<ProductDataItem>) {
        binding.recyclerView2.visibility = View.VISIBLE
        binding.recyclerView1.visibility = View.INVISIBLE
        binding.apply {
            recyclerView2.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recyclerView2.adapter = searchAadapter
            adapter.setData(productDataItem)
        }
    }

    private fun setUpRecyclerView(productDataItem: List<ProductDataItem>) {
        binding.apply {
            recyclerView1.visibility = View.VISIBLE
            recyclerView2.visibility = View.INVISIBLE
        }
        binding.apply {
            recyclerView1.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recyclerView1.adapter = adapter
            adapter.setData(productDataItem)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.button_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnClickListener { binding.recyclerView1.visibility = View.INVISIBLE }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

}