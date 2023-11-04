package com.rxs.pomodorotracker.presentation.task_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rxs.pomodorotracker.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private val viewModel: TaskListViewModel by viewModels()

    @Inject
    lateinit var taskListAdapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        setupView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserve()
    }

    private fun startObserve() {
        viewModel.pomodoroData.observe(viewLifecycleOwner) {
            taskListAdapter.submitData(it.taskList)
        }
    }

    private fun setupView() {
        binding.apply {
            rvFragmentTaskList.apply {
                layoutManager =
                    LinearLayoutManager(
                        this@TaskListFragment.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                adapter = taskListAdapter
            }
        }
    }
}