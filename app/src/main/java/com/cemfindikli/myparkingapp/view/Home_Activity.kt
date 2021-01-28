package com.cemfindikli.myparkingapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cemfindikli.myparkingapp.MainActivity
import com.cemfindikli.myparkingapp.R

class Home_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_)
    }
    fun searchpark(view: View){
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
