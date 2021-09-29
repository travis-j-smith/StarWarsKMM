package com.iamtravisjsmith.starwarskmm.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iamtravisjsmith.starwarskmm.android.people.PeopleScreen
import com.iamtravisjsmith.starwarskmm.android.person_details.PersonDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "people",
    ) {
        composable(
            route = "people",
        ) {
            PeopleScreen(
                navController = navController,
                peopleViewModel = hiltViewModel(),
            )
        }
        composable(
            route = "person/{personId}",
            arguments = listOf(
                navArgument("personId") { type = NavType.LongType }
            )
        ) {
            PersonDetailsScreen(
                personDetailsViewModel = hiltViewModel(),
            )
        }
    }
}
