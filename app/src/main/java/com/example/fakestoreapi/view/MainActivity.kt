package com.example.fakestoreapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fakestoreapi.R
import com.example.fakestoreapi.view.login.LoginFragment
import com.example.fakestoreapi.view.product_details.ProductDetailsFragment
import com.example.fakestoreapi.view.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ProductDetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}


