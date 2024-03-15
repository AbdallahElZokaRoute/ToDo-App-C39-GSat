package com.route.todo_app_c39_gsat.bindingAdapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("error")
fun TextInputEditText.setCustomError(customError: String?) {
    error = customError
}