package com.facom.facomemfoco.presentation.util.mask

import android.widget.EditText

class MaskTextChangedListener @JvmOverloads constructor(
        private val mask: String,
        editText: EditText,
        reversed: Boolean = false
) : BaseMaskTextChangedListener(editText, reversed) {

    override fun applyMask(text: String?, reversed: Boolean): String? {
        return if (reversed) applyReversedMask(this.mask, text) else applyMask(this.mask, text!!)
    }
}
