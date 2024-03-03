package com.route.todo_app_c39_gsat

import android.app.Application
import com.route.todo_app_c39_gsat.database.TaskDatabase

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TaskDatabase.init(this)
    }
}