package com.rxs.pomodorotracker.model

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rxs.pomodorotracker.TimerActivity
import com.rxs.pomodorotracker.databinding.ItemTasksBinding

class TaskAdapter(
    private val tasks: List<Task>
) : RecyclerView.Adapter<TaskAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val itemBinding =
            ItemTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    inner class TasksViewHolder(private val itemBinding: ItemTasksBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(task: Task) = with(itemBinding) {
            tvTasksName.text = task.taskName
            tvTasksCompleteTomatoes.text = "${task.tomatoesCount} of ${task.tomatoesGoal}"
            tvTasksWorkTime.text = "${task.workTime}:00"
            tvTasksRelaxTime.text = "${task.relaxTime}:00"
            btnOpenTask.setOnClickListener {
                val context = root.context
                Intent(context, TimerActivity::class.java).also {
                    it.putExtra(
                        "EXTRA_CURRENT_TASK",
                        task
                    )
                    context.startActivity(it)
                }
            }
        }
    }
}