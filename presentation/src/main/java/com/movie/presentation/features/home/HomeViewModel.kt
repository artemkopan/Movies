package com.movie.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.movie.domain.entities.Media
import com.movie.domain.features.home.GetMediaListUseCase
import com.movie.presentation.base.AppViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMediaListUseCase: GetMediaListUseCase
) : AppViewModel() {


    private val _mediaListLiveData = MutableLiveData<List<Media>>()
    val mediaListLiveData: LiveData<List<Media>> = _mediaListLiveData


    init {
        scope.launch(exceptionHandler { }) {
            _mediaListLiveData.postValue(getMediaListUseCase.execute())
        }
    }


}