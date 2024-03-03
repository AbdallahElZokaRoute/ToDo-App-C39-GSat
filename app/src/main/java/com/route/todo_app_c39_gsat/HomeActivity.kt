package com.route.todo_app_c39_gsat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.todo_app_c39_gsat.databinding.ActivityHomeBinding
import com.route.todo_app_c39_gsat.fragments.addTask.AddTaskBottomSheet
import com.route.todo_app_c39_gsat.fragments.SettingsFragment
import com.route.todo_app_c39_gsat.fragments.tasks.TasksListFragment

class HomeActivity : AppCompatActivity() {

    // DiffUtil
    //RecyclerView With multiple view Holders

    // support Libraries

    // AndroidX

    // Data base

    // Contacts ->
    //        add Contact
    //        add Contact
    // RAM -> Random Access Memory

    // Storage -> Files
    // Todos
    // 1- Play Basket ball 2/3/2024 false
    // 2- Play Basket ball 2/3/2024 false
    // 3- Play Basket ball 2/3/2024 false
    // Create
    // Read
    // Delete
    // Update

    // Files Manipulation
    //
    // Data base -> Files
    //
    // SQL Server - MYSQL

    // Lack Of resources
    // SQLite
    // Room
    // SQLite


    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tasks -> {
                    pushFragment(TasksListFragment())
                }

                R.id.settings -> {
                    pushFragment(SettingsFragment())

                }
            }

            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.tasks
        binding.fabAddTask.setOnClickListener {
            val bottomSheetFragment = AddTaskBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, null)
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.fragmentContainer.id, fragment)
            .commit()
    }
}