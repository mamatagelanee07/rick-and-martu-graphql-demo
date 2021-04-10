package com.andigeeky.rickandmorty.characters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.characters.model.Character
import com.andigeeky.rickandmorty.characters.model.Gender

class CharactersAdapter(val onItemClick: (characterId: Character) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {
    private val characters = mutableListOf<Character>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        characters[position].url?.let {
            holder.image.load(it)
        }
        holder.title.text = holder.title.context.getString(
            R.string.characters_title_character_name,
            characters[position].id?.id.orEmpty(),
            characters[position].name.orEmpty()
        )
        holder.subtitle.text = holder.subtitle.context.getString(
            R.string.characters_subtitle_no_of_episodes,
            characters[position].episodes.size
        )
        holder.imageGender.setImageResource(
            when(characters[position].gender){
                Gender.FEMALE -> R.drawable.ic_female_black_24dp
                Gender.MALE -> R.drawable.ic_male_black_24dp
                Gender.GENDERLESS -> R.drawable.ic_transgender_black_24dp
                Gender.UNKNOWN -> R.drawable.ic_transgender_black_24dp
            }
        )
        holder.itemView.setOnClickListener {
            onItemClick(characters[position])
        }
    }

    override fun getItemCount() = characters.size

    fun submitList(items: List<Character>) {
        characters.clear()
        characters.addAll(items)
        notifyDataSetChanged()
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.item_image_character)
        var imageGender: ImageView = view.findViewById(R.id.item_image_character_gender)
        var title: TextView = view.findViewById(R.id.item_title_character)
        var subtitle: TextView = view.findViewById(R.id.item_subtitle_character)
    }
}