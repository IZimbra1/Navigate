package com.example.navigate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigate.ui.theme.NavigateTheme



import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigateTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeView(navController) }
                    composable("detail/{name}") { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: "World"
                        DetailView(navController, name)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavController, name: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail View") },
                navigationIcon = {
                    Button(onClick = { navController.popBackStack() }) {
                        Text("Atras")
                    }
                },
                actions = {
                    Button(onClick = { navController.navigate("home") }) {
                        Text("Home")
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = "Received name: $name",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeView(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = { navController.navigate("detail/Android") }) {
                Text("Inicio")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigateTheme {
        Greeting("Android")
    }
}

