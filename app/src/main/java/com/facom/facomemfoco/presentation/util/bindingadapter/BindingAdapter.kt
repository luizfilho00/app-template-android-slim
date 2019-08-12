package com.facom.facomemfoco.presentation.util.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facom.facomemfoco.R
import com.facom.facomemfoco.presentation.util.extensions.colorCompat

@BindingAdapter("toggleSelectedItemColor")
fun View.toggleSelectedItemColor(selected: Boolean) {
    if (selected) setBackgroundColor(context.colorCompat(R.color.colorDarkBlue))
    else setBackgroundColor(context.colorCompat(R.color.colorLightGray))
}

@BindingAdapter("toggleFontColor")
fun TextView.toggleFontColor(selected: Boolean) {
    if (selected) setTextColor(context.colorCompat(R.color.white))
    else setTextColor(context.colorCompat(R.color.colorDarkBlue))
}