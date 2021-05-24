package com.test.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.compose.ui.theme.MyApplicationTheme
import com.test.compose.utils.BarUtils
import com.test.compose.utils.NavDestinationConst
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class NewMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                ProvideWindowInsets {
                    Nav()
                }
            }

        }
    }
}

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavDestinationConst.MAIN) {
        composable(NavDestinationConst.MAIN) {
            MainPage()
        }
    }
}