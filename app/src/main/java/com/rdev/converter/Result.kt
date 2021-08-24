package com.rdev.converter

data class Result(var error: String?) {
    var min: Int = 0
    var sec: Int = 0

    override fun toString(): String {
        return "$min m $sec s"
    }
}