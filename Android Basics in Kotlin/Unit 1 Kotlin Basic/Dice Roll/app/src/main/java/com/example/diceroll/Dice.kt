package com.example.diceroll

class Dice(private val num: Int) {
    fun roll(): Int {
        return (1..num).random()
    }
}