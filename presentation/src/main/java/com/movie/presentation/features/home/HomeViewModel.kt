package com.movie.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movie.domain.entities.Media
import com.movie.domain.features.home.MediaHomeRepository
import com.movie.presentation.base.AppViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mediaHomeRepository: MediaHomeRepository
) : AppViewModel() {


    private val _mediaListLiveData = MutableLiveData<List<Media>>()
    val mediaListLiveData: LiveData<List<Media>> = _mediaListLiveData


    init {
        scope.launch(exceptionHandler { }) {
            _mediaListLiveData.postValue(mediaHomeRepository.getMediaList())
        }
    }


}