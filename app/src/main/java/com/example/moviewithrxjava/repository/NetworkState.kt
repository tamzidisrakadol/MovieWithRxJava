package com.example.moviewithrxjava.repository


enum class Status {
    Running,
    Success,
    Failed

}

class NetworkState(val status: Status, val msg: String) {

    companion object {

        val Loaded: NetworkState = NetworkState(Status.Success, "Success")
        val Loading: NetworkState = NetworkState(Status.Running, "Loading")
        val Failed: NetworkState = NetworkState(Status.Failed, "Failed")

    }

}