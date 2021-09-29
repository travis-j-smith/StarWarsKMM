package com.iamtravisjsmith.starwarskmm.android.people

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iamtravisjsmith.starwarskmm.android.shared.AppBar
import com.iamtravisjsmith.starwarskmm.entities.Person

@Composable
fun PeopleScreen(
    navController: NavController,
    peopleViewModel: PeopleViewModel,
) {
    val people: List<Person> by peopleViewModel.peopleLiveData.observeAsState(emptyList())

    Scaffold(
        topBar = { AppBar(title = "People from Star Wars") }
    ) {
        LazyColumn {
            items(people) { person ->
                PersonRow(person = person) {
                    navController.navigate("person/${person.id}")
                }
            }
        }
    }
}
