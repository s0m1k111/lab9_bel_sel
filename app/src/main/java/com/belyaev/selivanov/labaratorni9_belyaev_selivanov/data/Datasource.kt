package com.belyaev.selivanov.labaratorni9_belyaev_selivanov.data

import com.belyaev.selivanov.labaratorni9_belyaev_selivanov.R
import com.belyaev.selivanov.labaratorni9_belyaev_selivanov.model.Game


class Datasource {
    fun loadGameList(): List<Game> {
        return listOf(
            Game(
                R.string.game1_title,
                R.string.game1_description,
                R.string.game1_genre,
                R.string.game1_year,
                R.drawable.game1
            ),
            Game(
                R.string.game2_title,
                R.string.game2_description,
                R.string.game2_genre,
                R.string.game2_year,
                R.drawable.game2
            ),
            Game(
                R.string.game3_title,
                R.string.game3_description,
                R.string.game3_genre,
                R.string.game3_year,
                R.drawable.game3
            ),
            Game(
                R.string.game4_title,
                R.string.game4_description,
                R.string.game4_genre,
                R.string.game4_year,
                R.drawable.game4
            )
        )
    }
}