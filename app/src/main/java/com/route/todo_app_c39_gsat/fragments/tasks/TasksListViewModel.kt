package com.route.todo_app_c39_gsat.fragments.tasks


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todo_app_c39_gsat.database.Task
import com.route.todo_app_c39_gsat.database.TaskDatabase
import java.util.Calendar

class TasksListViewModel : ViewModel() {
    val tasksLiveData = MutableLiveData<List<Task>>()
    fun getTasksByDate(calendar: Calendar) {
        val list = TaskDatabase
            .getInstance()
            .getTasksDao()
            .getTasksByDate(calendar.time)
        tasksLiveData.value = list
    }
}
