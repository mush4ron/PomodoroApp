package com.rxs.pomodorotracker.common

fun Int.fromMinutesToText(): String = "$this: 00"

fun String.eraseMinutes(): Int = this.replace(" min", "").toInt()

fun concatPomodoroText(complete: Int, goal: Int): String = "$complete из $goal"