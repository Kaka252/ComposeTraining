package com.test.compose.maintab

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.LiveData
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.test.compose.App
import com.test.compose.R
import com.test.compose.model.User
import com.test.compose.ui.theme.MyApplicationTheme
import com.test.compose.utils.Resource
import com.test.compose.viewmodel.UserViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun UserPage(userViewModel: UserViewModel) {
    Log.d("StartPage", "UserPage")

    var loadArticleState by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.string_tab_user),
                        color = Color.White
                    )
                },
                backgroundColor = MaterialTheme.colors.primary, elevation = 0.dp,
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            SwipeToRefresh(
                userViewModel = userViewModel
            )
        }
    }
    if (!loadArticleState) {
        loadArticleState = true
        userViewModel.getUserList(1)
    }
}

@Composable
fun SwipeToRefresh(
    userViewModel: UserViewModel
) {
    val uiState by userViewModel.postUiState.observeAsState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = uiState == true),
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                // Pass the SwipeRefreshState + trigger through
                state = state,
                refreshTriggerDistance = trigger,
                // Enable the scale animation
                scale = true,
                // Change the color and shape
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary,
                shape = CircleShape,
            )
        },
        onRefresh = { userViewModel.getUserList(1) })
    {
        GetList(userLiveData = userViewModel.userLiveData) { position, user ->
            Toast.makeText(App.context, user.geekName, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun GetList(
    userLiveData: LiveData<Resource<List<User>>>,
    onClick: (position: Int, user: User) -> Unit
) {
    val resource by userLiveData.observeAsState()
    resource?.let {
        when (it.status) {
            Resource.SUCCESS -> {
                it.data?.let { userList: List<User> ->
                    // We save the scrolling position with this state
                    val scrollState = rememberLazyListState()
                    // We save the coroutine scope where our animated scroll will be executed
                    val coroutineScope = rememberCoroutineScope()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colors.background),
                        state = scrollState
                    ) {
                        itemsIndexed(userList) { position, user ->
                            CardItemRenderer(position = position, user, onClick)
                        }
                    }
                }
            }
            Resource.LOADING -> {

            }
            else -> {

            }
        }
    }
}

@Composable
fun CardItemRenderer(position: Int, user: User, onClick: (position: Int, user: User) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(position, user)
            }
            .padding(20.dp, 18.dp)
    ) {
        // Create references for the composables to constrain
        val (userName, userDesc, avatar, gender, content) = createRefs()

        Text(
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(avatar.start)
                width = Dimension.fillToConstraints
            },
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colors.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = user.geekName
        )

        Text(
            modifier = Modifier.constrainAs(userDesc) {
                top.linkTo(userName.bottom, 3.dp)
                start.linkTo(userName.start)
                end.linkTo(avatar.start)
                width = Dimension.fillToConstraints
            },
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = MaterialTheme.colors.secondaryVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = user.subTitle
        )

//        GlideImage(
//            modifier = Modifier
//            .height(46.dp)
//            .width(46.dp)
//            .clip(CircleShape)
//            .constrainAs(avatar) {
//                top.linkTo(parent.top)
//                end.linkTo(parent.end)
//            },
//            data =
//        ) {
//
//        }
        Image(
            modifier = Modifier
                .height(46.dp)
                .width(46.dp)
                .clip(CircleShape)
                .constrainAs(avatar) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            painter = rememberCoilPainter(
                request = user.geekAvatar,
                fadeIn = true,
                fadeInDurationMs = 250,
                previewPlaceholder = R.drawable.ic_launcher_background
            ),
//            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "avatar"
        )

        var genderIcon = 0;
        when (user.geekGender) {
            0 -> genderIcon = R.drawable.ic_gender_male_16
            1 -> genderIcon = R.drawable.ic_gender_female_16
            2 -> genderIcon = R.drawable.ic_gender_secret_16
        }
        Image(
            modifier = Modifier.constrainAs(gender) {
                top.linkTo(avatar.top)
                end.linkTo(avatar.end)
            },
            painter = painterResource(id = genderIcon),
            contentDescription = "gender"
        )

        Text(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(userDesc.bottom, 10.dp)
                start.linkTo(userName.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondaryVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = user.geekContent
        )

    }

    // 分割线
    Spacer(
        modifier = Modifier
            .height(6.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
//        UserPage()
    }
}