package com.example.android.hilt.data

import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@ActivityScoped
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource {

    override val description: String
        get() = "This is prod LoggerInMemoryDataSource"

    private val logs = LinkedList<Log>()

    init {
        println("")
    }

    override fun addLog(msg: String) {
        logs.addFirst(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        logs.clear()
    }
}