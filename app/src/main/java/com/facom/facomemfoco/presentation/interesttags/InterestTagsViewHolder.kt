package com.facom.facomemfoco.presentation.interesttags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ItemTagBinding
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.presentation.util.bindingadapter.toggleSelectedItemColor

class InterestTagsViewHolder(
        private var binding: ItemTagBinding,
        private var onItemSelectedCallback: (Tag) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(tag: Tag) {
        with(binding) {
            linearLayoutItemTag.setOnClickListener { handleTagClicked(tag, it) }
            textViewHolderTag.text = tag.name
        }
    }

    private fun handleTagClicked(tag: Tag, view: View) {
        with(tag) {
            selected = !selected
            view.toggleSelectedItemColor(selected)
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