package com.example.myapplication



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val tasks = remember { mutableStateListOf<String>() }

            NavHost(
                navController = navController,
                startDestination = "task_list"
            ) {
                composable("task_list") {
                    TaskListScreen(
                        tasks = tasks,
                        onFabClick = { navController.navigate("create_task") }
                    )
                }
                composable("create_task") {
                    CreateTaskScreen(
                        onSaveTask = { newTask ->
                            tasks.add(newTask)
                            navController.popBackStack()
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}