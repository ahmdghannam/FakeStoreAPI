package com.example.fakestoreapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.ActivityMainBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.local.StoreDataBase

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)
        sharedPreferencesUtil.userId = 1
        StoreDataBase.init(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragmentContainer)
//        NavigationUI.setupActionBarWithNavController(this,navController)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        navController.navigateUp()
        return true
    }
}


