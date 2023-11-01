package com.rxs.pomodorotracker

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.rxs.pomodorotracker.database.MyPreferences
import com.rxs.pomodorotracker.databinding.ActivityTimerBinding
import com.rxs.pomodorotracker.model.Task

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var currentTask: Task
    private lateinit var countDownTimer: CountDownTimer
    private var timeGoalInMillis: Long = 0
    private var timeLeftInMillis: Long = 0
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentTask = intent.getSerializableExtra("EXTRA_CURRENT_TASK") as Task
        setBaseOptions()
    }

    override fun onPause() {
        currentTask.currentTime = (timeLeftInMillis / 1000).toInt()
        MyPreferences(this).updateTask(currentTask)
        super.onPause()
    }

    private fun setBaseOptions() {
        // set tvs
        binding.tvCurrentTaskNameActTimer.text = currentTask.taskName
        binding.tvTomatoesInfo.text = "${currentTask.tomatoesCount}/${currentTask.tomatoesGoal}"
        if (currentTask.currentAction == "work") {
            timeLeftInMillis = if (currentTask.currentTime != 0) {
                currentTask.currentTime.toLong() * 1000
            } else {
                currentTask.workTime.toLong() * 60 * 1000
            }
            timeGoalInMillis = currentTask.workTime.toLong() * 60 * 1000
        } else {
            timeLeftInMillis = if (currentTask.currentTime != 0) {
                currentTask.currentTime.toLong() * 1000
            } else {
                currentTask.relaxTime.toLong() * 60 * 1000
            }
            timeGoalInMillis = currentTask.relaxTime.toLong() * 60 * 1000
        }
        updateTimer()
        // set buttons
        binding.btnPlayTimerAct.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        binding.btnSkipTimerAct.setOnClickListener {
            nextTomato()
        }
        binding.btnBackActTimer.setOnClickListener {
            currentTask.currentTime = (timeLeftInMillis / 1000).toInt()
            MyPreferences(this).updateTask(currentTask)
            finish()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                nextTomato()
            }
        }.start()

        isTimerRunning = true
        binding.btnPlayTimerAct.setBackgroundResource(R.drawable.btn_light_pause)
        binding.tvPlayOrPauseText.text = "Pause"
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        isTimerRunning = false
        binding.btnPlayTimerAct.setBackgroundResource(R.drawable.btn_light_play)
        binding.tvPlayOrPauseText.text = "Play"
    }

    private fun nextTomato() {
        if (currentTask.currentAction == "work") {
            currentTask.tomatoesCount = currentTask.tomatoesCount + 1
            binding.tvTomatoesInfo.text = "${currentTask.tomatoesCount}/${currentTask.tomatoesGoal}"
            binding.progressBarActTimer.progressDrawable =
                getDrawable(R.drawable.progress_bar_relax)
            timeGoalInMillis = currentTask.relaxTime.toLong() * 60 * 1000
            timeLeftInMillis = currentTask.relaxTime.toLong() * 60 * 1000
            currentTask.currentAction = "relax"
            binding.tvTimerNameActTimer.text = "Relax Time"
        } else {
            binding.progressBarActTimer.progressDrawable = getDrawable(R.drawable.progress_bar_work)
            timeGoalInMillis = currentTask.workTime.toLong() * 60 * 1000
            timeLeftInMillis = currentTask.workTime.toLong() * 60 * 1000
            currentTask.currentAction = "work"
            binding.tvTimerNameActTimer.text = "Work Time"
        }
        countDownTimer.cancel()
        updateTimer()
        startTimer()

        MyPreferences(this).updateTask(currentTask)
    }

    private fun updateTimer() {
        binding.tvCurrentTaskTimer.text =
            String.format("%02d:%02d", timeLeftInMillis / 1000 / 60, timeLeftInMillis / 1000 % 60)
        binding.progressBarActTimer.progress =
            ((timeLeftInMillis.toDouble() / timeGoalInMillis.toDouble()) * 100).toInt()
    }
}