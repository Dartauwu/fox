package com.example.fox.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.fox.data.repository.NetworkState
import com.example.fox.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable


class MainActivityViewModel (private val moviesRepository: MoviePagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList : LiveData<PagedList<Movie>> by lazy {
        moviesRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        moviesRepository.getNetworkState()
    }

    fun listIsEmpety(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}