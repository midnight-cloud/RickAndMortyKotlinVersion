package com.evg_ivanoff.rickmortycharacterswiki.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evg_ivanoff.rickmortycharacterswiki.R
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item_grid.view.*

class MainCharacterAdapter : RecyclerView.Adapter<MainCharacterAdapter.MainCharacterViewHolder>() {

    var characterList: List<Character> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCharClickListener: OnCharacterClickListener? = null

    inner class MainCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCharacterImage = itemView.ivCharacter
        val tvCharacterName = itemView.tvCharacterName
        val tvCharacterRace = itemView.tvCharacterRace
        val tvCharacterGender = itemView.tvCharacterGender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_grid, parent, false)
        return MainCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainCharacterViewHolder, position: Int) {
        val char = characterList[position]
        with(holder) {
            with(char) {
                tvCharacterName.text = name
                tvCharacterRace.text = species
                tvCharacterGender.text = gender
                Picasso.get().load(image).into(ivCharacterImage)
                itemView.setOnClickListener {
                    onCharClickListener?.onCharClick(this)
                }
            }
        }

    }

    override fun getItemCount() = characterList.size

    interface OnCharacterClickListener{
        fun onCharClick(character: Character)
    }

}