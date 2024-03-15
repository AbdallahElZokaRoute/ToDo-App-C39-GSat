package com.route.todo_app_c39_gsat.fragments.addTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todo_app_c39_gsat.database.Task
import com.route.todo_app_c39_gsat.database.TaskDatabase
import java.util.Calendar

class AddTaskViewModel : ViewModel() {
    lateinit var calendar: Calendar
    val title = MutableLiveData("")
    val titleError = MutableLiveData<String?>()
    val description = MutableLiveData("")
    val descriptionError = MutableLiveData<String?>()
    val isDone = MutableLiveData(false)


    private fun validateFields(): Boolean {
        if (title.value?.isEmpty() == true || title.value?.isBlank() == true) {
            titleError.value = "Required"
            return false
        } else
            titleError.value = null
        if (description.value?.isEmpty() == true || description.value?.isBlank() == true) {
            descriptionError.value = "Required"
            return false
        } else
            descriptionError.value = null

        return true
    }

    fun addTask() {
        if (validateFields()) {
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            TaskDatabase
                .getInstance()
                .getTasksDao()
                .insertTask(
                    Task(
                        title = title.value,
                        description = description.value,
                        date = calendar.time,
                        isDone = false
                    )
                )
            isDone.value = true
        }
    }
}
