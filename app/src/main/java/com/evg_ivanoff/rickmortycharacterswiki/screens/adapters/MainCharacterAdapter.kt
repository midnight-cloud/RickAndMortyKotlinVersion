package com.evg_ivanoff.rickmortycharacterswiki.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evg_ivanoff.rickmortycharacterswiki.databinding.CharacterItemGridBinding
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character as MyChar
import com.squareup.picasso.Picasso

class MainCharacterAdapter : ListAdapter<MyChar, MainCharacterAdapter.MainCharacterViewHolder>(CharDiffCallback()) {

    var onCharClickListener: ((MyChar) -> Unit)? = null

    inner class MainCharacterViewHolder(private val binding: CharacterItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: MyChar) {
            binding.tvCharacterName.text = item.name
            binding.tvCharacterRace.text = item.species
            binding.tvCharacterGender.text = item.gender
            Picasso.get().load(item.image).into(binding.ivCharacter)
            itemView.setOnClickListener {
                onCharClickListener?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCharacterViewHolder {
        val binding =
            CharacterItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainCharacterViewHolder, position: Int) {
        val char = currentList[position]
        holder.bindItems(char)
    }

}