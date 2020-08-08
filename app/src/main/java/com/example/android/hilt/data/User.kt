package com.example.android.hilt.data

import javax.inject.Inject

class User @Inject constructor() {
    val name: String = "jay"
    val surname: String = "huang"
}