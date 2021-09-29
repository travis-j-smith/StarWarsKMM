package com.iamtravisjsmith.starwarskmm.android.person_details

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iamtravisjsmith.starwarskmm.android.shared.AppBar
import com.iamtravisjsmith.starwarskmm.entities.Person

@Composable
fun PersonDetailsScreen(
    personDetailsViewModel: PersonDetailsViewModel = viewModel()
) {
    val title: String by personDetailsViewModel.title.observeAsState("")
    val person: Person? by personDetailsViewModel.personLiveData.observeAsState(null)

    Scaffold(
        topBar = { AppBar(title = title) }
    ) {
        person?.run {
            Text(text = "$name was born on $birthYear")
        }
    }
}
