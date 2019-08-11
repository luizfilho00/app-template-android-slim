package com.facom.facomemfoco.presentation.interesttags

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facom.facomemfoco.domain.entity.Tag

class InterestTagsAdapter(
        private var callback: (Tag) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Tag> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            InterestTagsViewHolder.inflate(parent, ::onCallback)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tag = items[position]
        tag.position = position
        (holder as? InterestTagsViewHolder)?.setupBinding(tag)
    }

    fun setItems(tags: List<Tag>) {
        this.items = tags
        notifyDataSetChanged()
    }

    private fun onCallback(tag: Tag) {
        callback.invoke(tag)
    }
}