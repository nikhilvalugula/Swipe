package com.example.swipe.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.swipe.databinding.FragmentAddProductBinding
import com.example.swipe.networkclient.RetrofitClient
import com.example.swipe.repo.AddProductsRepo
import com.example.swipe.util.Coroutines
import com.example.swipe.viewModels.AddProductViewModel
import com.example.swipe.viewModels.AddProductViewModelFactory


class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding

    private lateinit var model: AddProductViewModel

    private val myAPi = RetrofitClient.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(LayoutInflater.from(this.requireContext()))
        return binding.root
    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(
            this,
            AddProductViewModelFactory(AddProductsRepo(myAPi))
        )[AddProductViewModel::class.java]

        binding.buttonAdd.setOnClickListener {
            val productName = binding.productName.text.toString().trim()
            val productPrice = binding.productPrice.text.toString().trim()
            val productTax = binding.productTax.text.toString().trim()
            if (productName.isEmpty()) {
                binding.productName.error = "product Name Required"
                binding.productName.requestFocus()
                return@setOnClickListener
            }
            if (productPrice.isEmpty()) {
                binding.productPrice.error = "Price Required"
                binding.productPrice.requestFocus()
                return@setOnClickListener
            }
            if (productTax.isEmpty()) {
                binding.productTax.error = "Tax Required"
                binding.productTax.requestFocus()
                return@setOnClickListener
            }
            pushProductDetails()
        }
    }

    //This method is used to post the details of products while add button is clicked.x
    private fun pushProductDetails() {
        val productName = binding.productName.text.toString().trim()
        val productPrice = binding.productPrice.text.toString().trim()
        val productTax = binding.productTax.text.toString().trim()
        val productType = java.lang.String.valueOf(binding.productType.selectedItem)
        Coroutines.main {
            val pushResponse =
                model.pushPosts(
                    productName,
                    productType,
                    productPrice.toDouble(),
                    productTax.toDouble()
                )

            if (pushResponse.success) {
                val action = AddProductFragmentDirections.actionViewProduct()
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
            Toast.makeText(
                context,
                pushResponse.message,
                Toast.LENGTH_LONG
            ).show()
        }

    }
}
