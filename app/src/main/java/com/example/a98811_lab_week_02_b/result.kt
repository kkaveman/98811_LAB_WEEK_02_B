package com.example.a98811_lab_week_02_b

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.widget.TextView
import android.content.Intent
import android.app.Activity
import android.widget.Button
import androidx.activity.result.registerForActivityResult
import android.widget.Toast


class result : AppCompatActivity() {

    companion object{
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"

    }

    //private val backButton = findViewById<Button>(R.id.back_button) //this crashes.. why?? because this works after onCreate
    private val backButton : Button
    get() = findViewById<Button>(R.id.back_button) //this works before onCreate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        // backButton = findViewById<Button>(R.id.back_button)

        backButton.setOnClickListener {

            Toast.makeText(this,"Returned home",Toast.LENGTH_LONG).show()
            finish()
        }



        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)

            val backgroundscreen = findViewById<ConstraintLayout>(R.id.background_screen)

            try{
                backgroundscreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            }
            catch (ex: IllegalArgumentException){
                Intent().let{
                    errorIntent ->
                    errorIntent.putExtra(ERROR_KEY,true)
                    setResult(Activity.RESULT_OK,errorIntent)
                    finish()
                }
            }
            val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,colorCode?.uppercase())
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.background_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}