package com.rxs.pomodorotracker.data.model

data class PomodoroData(
    var tasksList: List<Task> = emptyList(),
    var recentTask: Task? = null
)
