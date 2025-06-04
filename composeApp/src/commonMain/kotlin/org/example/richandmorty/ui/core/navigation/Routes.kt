package org.example.richandmorty.ui.core.navigation

sealed class Routes(val route: String){
    data object Home: Routes("home")

    //Bottomnav
    data object Episodes: Routes("episodes")
    data object Characters: Routes("characters")

}