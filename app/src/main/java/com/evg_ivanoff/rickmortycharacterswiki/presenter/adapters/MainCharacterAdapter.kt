package com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evg_ivanoff.rickmortycharacterswiki.databinding.CharacterItemGridBinding
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel
import com.squareup.picasso.Picasso

class MainCharacterAdapter :
    PagingDataAdapter<CharacterModel, MainCharacterAdapter.MainCharacterViewHolder>(
        CharDiffCallback()
    ) {

    var onCharClickListener: ((CharacterModel) -> Unit)? = null

    inner class MainCharacterViewHolder(private val binding: CharacterItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: CharacterModel) {
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
        val char = getItem(position)!!
        holder.bindItems(char)
    }

}