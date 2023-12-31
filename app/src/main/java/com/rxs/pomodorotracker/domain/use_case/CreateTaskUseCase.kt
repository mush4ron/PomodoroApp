package com.rxs.pomodorotracker.domain.use_case

import com.rxs.pomodorotracker.common.DispatcherProvider
import com.rxs.pomodorotracker.data.model.Task
import com.rxs.pomodorotracker.domain.repository.DataRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun invoke(task: Task) {
        withContext(dispatcherProvider.io) {
            val sharedPomodoroData = dataRepository.get()
            sharedPomodoroData.taskList.add(task)
            dataRepository.save(data = sharedPomodoroData)
        }
    }
}