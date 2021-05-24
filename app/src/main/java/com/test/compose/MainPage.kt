package com.test.compose

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.compose.maintab.*
import com.test.compose.viewmodel.MainTabViewModel
import com.test.compose.viewmodel.MainViewModelFactory
import com.test.compose.viewmodel.UserViewModel
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun MainPage() {
    Log.d("StartPage", "MainPage")
    val viewModel: MainTabViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel(factory = MainViewModelFactory())
    val position by viewModel.position.observeAsState()
    val tabs = CourseTabs.values()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                Modifier.navigationBarsHeight(additional = 50.dp)
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .navigationBarsPadding(),
                        icon = {
                            Icon(
                                painter = painterResource(tab.icon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(tab.title)
                            )
                        },
                        selected = tab == position,
                        onClick = {
                            viewModel.onPositionChanged(tab)
                        },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (position) {
            CourseTabs.TAB_USER -> UserPage(userViewModel)
            CourseTabs.TAB_SEARCH -> WeatherPage()
            CourseTabs.TAB_CONTACT -> ContactPage()
            CourseTabs.TAB_MY -> MyPage()
        }
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    TAB_USER(R.string.string_tab_user, R.drawable.ic_action_coor_black),
    TAB_SEARCH(R.string.string_tab_search, R.drawable.ic_action_helper_black),
    TAB_CONTACT(R.string.string_tab_contact, R.drawable.ic_action_share_black),
    TAB_MY(R.string.string_tab_my, R.drawable.ic_action_favor_off_black)
}