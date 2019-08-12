package com.facom.facomemfoco.presentation.interesttags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ItemTagBinding
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.presentation.util.bindingadapter.toggleFontColor
import com.facom.facomemfoco.presentation.util.bindingadapter.toggleSelectedItemColor
import com.facom.facomemfoco.presentation.util.extensions.setVisible

class InterestTagsViewHolder(
        private var binding: ItemTagBinding,
        private var onItemSelectedCallback: (Tag) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(tag: Tag) {
        with(binding) {
            linearLayoutItemTag.setOnClickListener { handleTagClicked(tag) }
            itemTag = tag
        }
    }

    private fun handleTagClicked(tag: Tag) {
        with(tag) {
            selected = !selected
            with(binding) {
                linearLayoutItemTag.toggleSelectedItemColor(selected)
                textViewHolderTag.toggleSelectedItemColor(selected)
                textViewHolderTag.toggleFontColor(selected)
                imageViewChecked.setVisible(selected)
            }
            onItemSelectedCallback.invoke(this)
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