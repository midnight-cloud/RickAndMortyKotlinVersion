package com.evg_ivanoff.rickmortycharacterswiki.presenter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evg_ivanoff.rickmortycharacterswiki.domain.models.CharacterModel

class CharDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem == newItem
    }
}