package com.example.xumengyin.mypractice.testactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.xumengyin.mypractice.R

class KotlinActivity : AppCompatActivity() {

    var a :String ="1";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }
    fun parseData(str: String):Int?
    {
        return str.toIntOrNull();
    }
}
