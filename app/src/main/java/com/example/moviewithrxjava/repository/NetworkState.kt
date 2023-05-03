package com.example.moviewithrxjava.repository


enum class Status{
    Running,
    Success,
    Failed

}
class NetworkState(val status: Status,val msg:String) {

    companion object{

        private val Loaded:NetworkState
        private val Loading:NetworkState
        private val Failed:NetworkState

        init {
            Loaded = NetworkState(Status.Success,"Success")
            Loading = NetworkState(Status.Running,"Loading")
            Failed = NetworkState(Status.Failed,"Failed")
        }

    }

}