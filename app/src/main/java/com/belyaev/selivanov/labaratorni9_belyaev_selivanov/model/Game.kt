package com.belyaev.selivanov.labaratorni9_belyaev_selivanov.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Game(
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val genreResourceId: Int,
    @StringRes val yearResourceId: Int,
    @DrawableRes val imageResourceId: Int
)