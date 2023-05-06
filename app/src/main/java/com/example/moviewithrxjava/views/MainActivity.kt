package com.example.moviewithrxjava.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviewithrxjava.R
import com.example.moviewithrxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text.setOnClickListener {
            startActivity(Intent(this@MainActivity,MovieDetailActivity::class.java))
        }



    }
}