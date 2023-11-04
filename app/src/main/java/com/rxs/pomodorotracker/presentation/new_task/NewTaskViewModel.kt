package com.rxs.pomodorotracker.presentation.new_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.pomodorotracker.data.model.Task
import com.rxs.pomodorotracker.domain.use_case.CreateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    fun createTask(
        name: String,
        workTimeInMinutes: Int,
        relaxTimeInMinutes: Int,
        tomatoesGoal: Int
    ) {
        viewModelScope.launch {
            createTaskUseCase.createTask(
                Task(
                    name = name,
                    workTimeInMinutes = workTimeInMinutes,
                    relaxTimeInMinutes = relaxTimeInMinutes,
                    tomatoesGoal = tomatoesGoal
                )
            )
        }
    }
}