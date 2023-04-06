package com.example.superheroes.Data

import com.example.superheroes.R
import com.example.superheroes.model.SuperHero

class DataSource() {
    fun getList(): List<SuperHero> {
        return listOf(
            SuperHero(
                R.string.nick_the_night_and_day_name,
                R.string.nick_the_night_and_day_detail,
                R.drawable.android_superhero1
            ),
            SuperHero(
                R.string.reality_protector_name,
                R.string.reality_protector_detail,
                R.drawable.android_superhero2
            ),
            SuperHero(
                R.string.andre_the_giant_name,
                R.string.andre_the_giant_detail,
                R.drawable.android_superhero3
            ),
            SuperHero(
                R.string.benjamin_the_brave_name,
                R.string.benjamin_the_brave_detail,
                R.drawable.android_superhero4
            ),
            SuperHero(
                R.string.magnificent_maru_name,
                R.string.magnificent_maru_detail,
                R.drawable.android_superhero5
            ),
            SuperHero(
                R.string.dynamic_yasmine_name,
                R.string.dynamic_yasmine_detail,
                R.drawable.android_superhero6
            ),
        )
    }
}