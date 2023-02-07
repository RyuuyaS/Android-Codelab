package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<View>(R.id.button)
        button.setOnClickListener {
            rollTwoDice()
        }
        rollTwoDice()
    }

    private fun rollTwoDice(): Unit {
        var image1 = findViewById<ImageView>(R.id.image1)
        var image2 = findViewById<ImageView>(R.id.image2)
        val dice1 = Dice(6).roll()
        val dice2 = Dice(6).roll()
        image1.setImageResource(numOfImage(dice1))
        image2.setImageResource(numOfImage(dice2))
        image1.contentDescription = dice1.toString()
        image2.contentDescription = dice2.toString()
    }

    private fun numOfImage(num: Int): Int {
        return when (num) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}