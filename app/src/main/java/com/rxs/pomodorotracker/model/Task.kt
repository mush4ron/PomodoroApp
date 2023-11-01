package com.rxs.pomodorotracker.model

import java.io.Serializable

data class Task(
    var taskName: String,
    var workTime: Int,
    var relaxTime: Int,
    var tomatoesCount: Int,
    var tomatoesGoal: Int,
    var currentTime: Int = 0,
    var currentAction: String = "work"
) : Serializable