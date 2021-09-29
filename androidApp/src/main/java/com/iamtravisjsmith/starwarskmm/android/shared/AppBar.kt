package com.iamtravisjsmith.starwarskmm.android.shared

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppBar(title: String) {
    TopAppBar(title = { Text(text = title) })
}
