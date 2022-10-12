package edu.robertconstantin.animeappcliient.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.robertconstantin.animeappcliient.core.presentation.navigation.BottomNavMenu
import edu.robertconstantin.animeappcliient.core.presentation.navigation.screen.BottomMenuScreen
import edu.robertconstantin.animeappcliient.core.ui.theme.AnimeAppCliientTheme
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_create_screen.HeroCreateScreen
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_favorites_screen.HeroFavoritesScreen
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen.HeroFeedScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeAppCliientTheme {

                //controlador de navegacion
                val navController = rememberNavController()
                //propiedad que determina en todo moomento que pantalla esta en nuestra pila
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                //propiedad que constituye el andamio sobre el que se va a montar los
                //diferentes elementos que conforman nuestra app. Por ejemplo la barra de navegacion
                val scaffoldState = rememberScaffoldState()
                //variable que contiene el contexto de la aplicacion.
                val context = LocalContext.current

                //Este es el contenedor principal de la app y tiene nuestro color de fondo.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Nuestro andamio que conformara la pantalla general,  tendra una serie de elementos.
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomNavMenu(
                                navController = navController,
                                //Implementacion de la lambda de navegacion.
                                onBottomIconClick = { route ->
                                    navController.navigate(route) {
                                        navController.graph.startDestinationRoute?.let { route ->
//                                            popUpTo(route) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            )

                        }
                    ) { paddingValues ->
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding())
                            .verticalScroll(rememberScrollState())) {

//                            Text("Bottom app bar padding:  $paddingValues")
//
//                            repeat(50) {
//                                Text(it.toString())
//                            }
                        }
                        NavHost(
                            navController = navController,
                            startDestination = BottomMenuScreen.HeroFeedScreen.route) {

                            composable(route = BottomMenuScreen.HeroFeedScreen.route) {
                                HeroFeedScreen()
                            }

                            composable(route = BottomMenuScreen.HeroFavoritesScreen.route) {
                                HeroFavoritesScreen()
                            }

                            composable(route = BottomMenuScreen.HeroCreateScreen.route) {
                                HeroCreateScreen()
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimeAppCliientTheme {
        Greeting("Android")
    }
}