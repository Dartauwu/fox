package com.example.fox.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fox.data.api.POST_PER_PAGE
import com.example.fox.data.api.TheMovieDBinterface
import com.example.fox.data.repository.MovieDataSource
import com.example.fox.data.repository.MovieDataSourceFactory
import com.example.fox.data.repository.NetworkState
import com.example.fox.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : TheMovieDBinterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
     movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config :PagedList.Config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(POST_PER_PAGE)
                .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()
        return moviePagedList

    }

    fun getNetworkState(): LiveData<NetworkState>{
        return Transformations.switchMap<MovieDataSource,NetworkState>(
                movieDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}