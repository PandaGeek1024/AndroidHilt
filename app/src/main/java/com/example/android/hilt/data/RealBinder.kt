package com.example.android.hilt.data

import javax.inject.Inject

class RealBinder @Inject constructor() : TestBinder {
    override val description: String
        get() = "RealBinder"
}