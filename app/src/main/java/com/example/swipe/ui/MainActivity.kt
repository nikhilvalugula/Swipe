package com.example.swipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.swipe.databinding.ActvityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActvityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

}