package com.iamtravisjsmith.starwarskmm.android.person_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
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
class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val starWarsRepository: StarWarsRepository,
) : ViewModel() {

    private val personId = savedStateHandle.get<Long>("personId")!!

    val personLiveData: LiveData<Person?> =
        liveData(context = viewModelScope.coroutineContext) {
            withContext(Dispatchers.IO) {
                emit(starWarsRepository.getPerson(personId))
            }
        }

    val title: LiveData<String> = MediatorLiveData<String>().apply {
        value = "Loading Person #$personId"
        addSource(personLiveData) { person ->
            value = person?.name ?: value
        }
    }
}
