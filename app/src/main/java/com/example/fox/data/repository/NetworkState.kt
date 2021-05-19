package com.example.fox.data.repository

import com.example.fox.data.repository.NetworkState.Companion.ENDOFLIST

enum class Status {

    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS,"Success")

            LOADING = NetworkState(Status.RUNNING,"Running")

            ERROR = NetworkState(Status.FAILED,"something went wrong")

            ENDOFLIST = NetworkState(Status. FAILED, "You have reached the end")
        }
    }

}