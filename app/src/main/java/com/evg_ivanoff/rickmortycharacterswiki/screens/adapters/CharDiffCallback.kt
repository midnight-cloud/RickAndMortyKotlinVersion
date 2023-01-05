package com.evg_ivanoff.rickmortycharacterswiki.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.evg_ivanoff.rickmortycharacterswiki.pojo.character.Character as MyChar

class CharDiffCallback : DiffUtil.ItemCallback<MyChar>() {
    override fun areItemsTheSame(
        oldItem: MyChar,
        newItem: MyChar
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MyChar,
        newItem: MyChar
    ): Boolean {
        return oldItem == newItem
    }
}