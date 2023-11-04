package com.rxs.pomodorotracker.presentation.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.rxs.pomodorotracker.R
import com.rxs.pomodorotracker.common.eraseMinutes
import com.rxs.pomodorotracker.databinding.FragmentNewTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private val viewModel: NewTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(layoutInflater)
        setupView()

        return binding.root
    }

    private fun setupView() {
        binding.apply {
            btnFragmentNewTaskCreate.setOnClickListener {
                viewModel.createTask(
                    name = etFragmentNewTaskName.text.toString(),
                    workTimeInMinutes = tvFragmentNewTaskWorkTime.text.toString().eraseMinutes(),
                    relaxTimeInMinutes = tvFragmentNewTaskRelaxTime.text.toString().eraseMinutes(),
                    tomatoesGoal = tvFragmentNewTaskTomato.text.toString().toInt()
                )

                Navigation.createNavigateOnClickListener(
                    R.id.action_timerFragment_to_taskListFragment
                ).onClick(it)
            }
        }
    }
}