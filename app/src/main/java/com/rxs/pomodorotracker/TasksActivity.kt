package com.rxs.pomodorotracker

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rxs.pomodorotracker.database.MyPreferences
import com.rxs.pomodorotracker.databinding.ActivityTasksBinding
import com.rxs.pomodorotracker.model.Task
import com.rxs.pomodorotracker.model.TaskAdapter

class TasksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasksBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateAdapter()

        binding.btnAddNewTask.setOnClickListener {
            Intent(applicationContext, NewTaskActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        updateAdapter()
        super.onResume()
    }

    private fun updateAdapter() {
        val tasks = MyPreferences(this).getTasks()
        val adapter = TaskAdapter(tasks)
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = LinearLayoutManager(applicationContext)
    }
}