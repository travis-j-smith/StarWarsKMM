package com.iamtravisjsmith.starwarskmm.android.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.iamtravisjsmith.starwarskmm.entities.Person
import com.iamtravisjsmith.starwarskmm.repositories.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val starWarsRepository: StarWarsRepository,
) : ViewModel() {

    val peopleLiveData: LiveData<List<Person>> =
        liveData(context = viewModelScope.coroutineContext) {
            withContext(Dispatchers.IO) {
                emit(starWarsRepository.getPeople())
            }
        }
}
