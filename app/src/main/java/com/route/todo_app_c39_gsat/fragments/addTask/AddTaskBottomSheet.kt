package com.route.todo_app_c39_gsat.fragments.addTask

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo_app_c39_gsat.database.Task
import com.route.todo_app_c39_gsat.database.TaskDatabase
import com.route.todo_app_c39_gsat.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    lateinit var viewModel: AddTaskViewModel

    // Data Binding + Binding Adapters
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.calendar = Calendar.getInstance()
        binding.selectTimeTv.setOnClickListener {
            val picker = TimePickerDialog(
                requireContext(),
                object : OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        viewModel.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        viewModel.calendar.set(Calendar.MINUTE, minute)
                        binding.selectTimeTv.text = "$hourOfDay:$minute"
                    }
                },
                viewModel.calendar.get(Calendar.HOUR_OF_DAY),
                viewModel.calendar.get(Calendar.MINUTE),
                false
            )
            picker.show()
        }
        binding.selectDateTv.setOnClickListener {
            val picker = DatePickerDialog(
                requireContext(),
                object : OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        viewModel.calendar.set(Calendar.YEAR, year)
                        viewModel.calendar.set(Calendar.MONTH, month)
                        viewModel.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        binding.selectDateTv.text = "$dayOfMonth/${month + 1}/$year"
                    }
                },
                viewModel.calendar.get(Calendar.YEAR),
                viewModel.calendar.get(Calendar.MONTH),
                viewModel.calendar.get(Calendar.DAY_OF_MONTH)
            )

            picker.show()
        }
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            Log.e("TAG", "subscribeToLiveData:  $title")
        }
        viewModel.description.observe(viewLifecycleOwner) { title ->
            Log.e("TAG", "subscribeToLiveData:  $title")
        }
        viewModel.isDone.observe(viewLifecycleOwner) {
            if (it) {
                dismiss()
            }
        }
    }

}