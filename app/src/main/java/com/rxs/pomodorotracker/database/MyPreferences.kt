package com.rxs.pomodorotracker.database

import android.content.Context
import com.rxs.pomodorotracker.model.Task
import com.rxs.pomodorotracker.model.TasksList
import com.rxs.pomodorotracker.model.toJson
import com.rxs.pomodorotracker.model.toTasksList

class MyPreferences(
    context: Context
) {
    companion object {
        private const val PREFS_NAME = "myPrefs"
        private const val KEY_TASKS = "tasks"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveTasks(tasks: List<Task>) {
        val tasksList = TasksList(tasks)
        prefs.edit().putString(KEY_TASKS, tasksList.toJson()).apply()
    }

    fun updateTask(updatedTask: Task) {
        val tasks = getTasks()
        for (i in 0 until tasks.size) {
            if (tasks[i].taskName == updatedTask.taskName) {
                tasks[i] = updatedTask
            }
        }
        saveTasks(tasks)
    }

    fun addTask(newTask: Task) {
        val tasks = getTasks()
        tasks.add(newTask)
        saveTasks(tasks)
    }

    fun getTasks(): MutableList<Task> {
        val tasksListJson = prefs.getString(KEY_TASKS, null)
        return if (!tasksListJson.isNullOrEmpty()) {
            tasksListJson.toTasksList().list.toMutableList()
        } else {
            mutableListOf()
        }
    }
}