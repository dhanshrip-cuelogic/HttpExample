package com.example.httpexample


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val get_button = findViewById<Button>(R.id.get_button)
        get_button.setOnClickListener {
            println("button clicked")
            val httpRequest = HttpRequestTask()
            httpRequest.execute( "DELETE")
        }

    }
}