package com.example.affirmationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affirmationapp.adapter.ItemAdapter
import com.example.affirmationapp.data.Datasource
import com.example.affirmationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDataSet = Datasource().loadAffirmations()
        binding.recyclerView.adapter = ItemAdapter(this, myDataSet)
        binding.recyclerView.setHasFixedSize(true)
    }
}