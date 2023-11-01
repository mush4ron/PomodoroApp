package com.rxs.pomodorotracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rxs.pomodorotracker.database.MyPreferences
import com.rxs.pomodorotracker.databinding.ActivityNewTaskBinding
import com.rxs.pomodorotracker.model.Task

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            btnWorkTimePlus.setOnClickListener {
                plusWorkTime()
            }
            btnWorkTimeMinus.setOnClickListener {
                minusWorkTime()
            }

            btnRelaxTimePlus.setOnClickListener {
                plusRelaxTime()
            }
            btnRelaxTimeMinus.setOnClickListener {
                minusRelaxTime()
            }

            btnTomatoesPlus.setOnClickListener {
                plusTomatoes()
            }
            btnTomatoesMinus.setOnClickListener {
                minusTomatoes()
            }

            btnSaveNewTask.setOnClickListener {
                saveNewTask()
            }
            btnBackToTasks.setOnClickListener {
                finish()
            }
        }
    }

    private fun saveNewTask() {
        val taskName = binding.etTaskName.text.toString()
        val workTime = binding.tvWorkTimeValue.text.toString()
            .replace(" min", "").toInt()
        val relaxTime = binding.tvRelaxTimeValue.text.toString()
            .replace(" min", "").toInt()
        val tomatoesCount = 0
        val tomatoesGoal = binding.tvTomatoesValue.text.toString().toInt()
        MyPreferences(this).addTask(Task(taskName, workTime, relaxTime, tomatoesCount, tomatoesGoal))
        finish()
    }

    private fun plusWorkTime() {
        with(binding) {
            var minutes = tvWorkTimeValue.text.toString()
                .replace(" min", "").toInt()
            if (isPlusableMinutes(minutes)) {
                tvWorkTimeValue.text = "${++minutes} min"
            }
        }
    }

    private fun minusWorkTime() {
        with(binding) {
            var minutes = tvWorkTimeValue.text.toString()
                .replace(" min", "").toInt()
            if (isMinusableMinutes(minutes)) {
                tvWorkTimeValue.text = "${--minutes} min"
            }
        }
    }

    private fun plusRelaxTime() {
        with(binding) {
            var minutes = tvRelaxTimeValue.text.toString()
                .replace(" min", "").toInt()
            if (isPlusableMinutes(minutes)) {
                tvRelaxTimeValue.text = "${++minutes} min"
            }
        }
    }

    private fun minusRelaxTime() {
        with(binding) {
            var minutes = tvRelaxTimeValue.text.toString()
                .replace(" min", "").toInt()
            if (isMinusableMinutes(minutes)) {
                tvRelaxTimeValue.text = "${--minutes} min"
            }
        }
    }

    private fun plusTomatoes() {
        with(binding) {
            var minutes = tvTomatoesValue.text.toString().toInt()
            if (isPlusableTomatoes(minutes)) {
                tvTomatoesValue.text = "${++minutes}"
            }
        }
    }

    private fun minusTomatoes() {
        with(binding) {
            var minutes = tvTomatoesValue.text.toString().toInt()
            if (isMinusableTomatoes(minutes)) {
                tvTomatoesValue.text = "${--minutes}"
            }
        }
    }

    private fun isPlusableMinutes(x: Int) = x < 60

    private fun isMinusableMinutes(x: Int) = x > 10

    private fun isPlusableTomatoes(x: Int) = x < 24

    private fun isMinusableTomatoes(x: Int) = x > 2
}