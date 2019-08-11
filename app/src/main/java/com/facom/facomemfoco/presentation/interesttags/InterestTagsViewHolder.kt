package com.facom.facomemfoco.presentation.interesttags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ItemTagBinding
import com.facom.facomemfoco.domain.entity.Tag

class InterestTagsViewHolder(
        private var binding: ItemTagBinding,
        private var onItemSelectedCallback: (Tag) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(tag: Tag) {
        with(binding) {
            linearLayoutItemTag.setOnClickListener { onItemSelectedCallback.invoke(tag) }
            textViewHolderTag.text = tag.name
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?, callback: (Tag) -> Unit) = InterestTagsViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent?.context),
                        R.layout.item_tag,
                        parent,
                        false
                ), callback
        )
    }
}