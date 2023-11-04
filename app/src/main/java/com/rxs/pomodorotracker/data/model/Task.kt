package com.rxs.pomodorotracker.data.model

import com.rxs.pomodorotracker.common.ActionType

data class Task(
    var name: String = "Unnamed",
    var workTimeInMinutes: Int = 25,
    var relaxTimeInMinutes: Int = 7,
    var tomatoesComplete: Int = 0,
    var tomatoesGoal: Int = 8,
    var stopTimeInSeconds: Int = 0,
    var stopType: ActionType = ActionType.WORK
)
