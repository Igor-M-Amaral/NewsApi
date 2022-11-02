package com.example.igormattos.newsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.igormattos.newsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


    }


}