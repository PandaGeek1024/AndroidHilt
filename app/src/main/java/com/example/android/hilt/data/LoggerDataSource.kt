package com.example.android.hilt.data

interface LoggerDataSource {
    val description: String
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}