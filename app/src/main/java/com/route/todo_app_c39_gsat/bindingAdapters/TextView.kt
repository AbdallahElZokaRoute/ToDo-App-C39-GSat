package com.route.todo_app_c39_gsat.bindingAdapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("formatDate")
fun TextView.formatDate(date: Date?) {
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    text = simpleDateFormat.format(date!!)
}