package com.iamtravisjsmith.starwarskmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}