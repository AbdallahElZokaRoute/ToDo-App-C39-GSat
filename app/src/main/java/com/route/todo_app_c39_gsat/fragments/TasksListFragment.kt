package com.route.todo_app_c39_gsat.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.todo_app_c39_gsat.adapters.TasksAdapter
import com.route.todo_app_c39_gsat.database.TaskDatabase
import com.route.todo_app_c39_gsat.databinding.FragmentTasksBinding
import java.util.Calendar

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter: TasksAdapter
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TasksAdapter(null)
        binding.rvTasks.adapter = adapter
        calendar = Calendar.getInstance() // 2/3/2024 2:10PM 1212313211
        //   2/3/2024 2:12PM                                1212313213
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month - 1)
            // Library January -> 1
            // calendar january -> 0
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            Log.e("TAG", "onViewCreated: CALENDAR LIBRARY DATE ${date.month}")
            Log.e("TAG", "onViewCreated: Calendar ${calendar.get(Calendar.MONTH)}")
            calendar.set(Calendar.DAY_OF_MONTH, date.day)
            val tasks = TaskDatabase
                .getInstance(requireContext())
                .getTasksDao()
                .getTasksByDate(calendar.time)
            adapter.updateData(tasks)
        }
    }
}