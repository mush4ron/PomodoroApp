package com.rxs.pomodorotracker.model

import com.google.gson.Gson

data class TasksList(val list: List<Task>)

fun TasksList.toJson(): String = Gson().toJson(this)

fun String.toTasksList(): TasksList = Gson().fromJson(this, TasksList::class.java)