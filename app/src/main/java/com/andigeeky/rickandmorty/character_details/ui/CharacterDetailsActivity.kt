package com.andigeeky.rickandmorty.character_details.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.andigeeky.rickandmorty.R

class CharacterDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        findNavController(R.id.character_details_nav_host_fragment)
            .setGraph(R.navigation.navigation_character_details, intent.extras)
    }
}