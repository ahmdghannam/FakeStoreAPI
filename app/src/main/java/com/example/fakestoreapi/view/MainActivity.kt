package com.example.fakestoreapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fakestoreapi.R
import com.example.fakestoreapi.view.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}


