package com.facom.facomemfoco.presentation.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.facom.facomemfoco.R
import com.facom.facomemfoco.presentation.util.extensions.colorCompat

@BindingAdapter("toggleSelectedItemColor")
fun View.toggleSelectedItemColor(selected: Boolean) {
    if (selected) setBackgroundColor(context.colorCompat(R.color.colorBlue))
    else setBackgroundColor(context.colorCompat(R.color.colorLightGray))
}