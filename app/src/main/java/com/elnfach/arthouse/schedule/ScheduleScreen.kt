package com.elnfach.arthouse.schedule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.R
import com.elnfach.arthouse.schedule.components.ModalBottomSheet
import com.elnfach.arthouse.schedule.components.ScheduleScreenSkeleton
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.SkeletonScreen
import com.elnfach.arthouse.utils.managers.UserManager
import com.elnfach.arthouse.utils.navigation.Router
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScheduleScreen(
    externalRouter: Router,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: ScheduleScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    // Refresh
    val isRefreshing by remember { mutableStateOf(false) }
    val stateRefresh= rememberPullRefreshState(isRefreshing, {viewModel.pullToRefresh()})
    // ^^^ Refresh ^^^

    val ctx = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var clickedItemId by remember { mutableStateOf("") }
    val toastIdCopied = Toast.makeText(context, stringResource(id = R.string.id_copied), Toast.LENGTH_SHORT)
    val toastACCopied = Toast.makeText(context, stringResource(id = R.string.access_code_copied), Toast.LENGTH_SHORT)

    val currentDate = LocalDate.now()
    val dateList = List(7) { index -> currentDate.plusDays(index.toLong()) }

    Scaffold(
        topBar = { MediumTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.schedule),
                    fontSize = 30.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }
        )
        },
        floatingActionButton = {
            if (UserManager.status)
            {
                FloatingActionButton(onClick = {
                    externalRouter.routeTo(Screen.AddNewLesson.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            LazyRow(Modifier.padding(horizontal = 24.dp))
            {
                items(dateList)
                { date ->
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(top = 12.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer
                            )
                            .clickable {
                                viewModel.saveDate(date)
                                viewModel.loadSchedule()
                            }
                    )
                    {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            when (date) {
                                currentDate -> Text(text = stringResource(id = R.string.today))
                                currentDate.plusDays(1) -> Text(text = stringResource(id = R.string.tomorrow))
                                else -> Text(text = date.format(DateTimeFormatter.ofPattern("d MMMM")))
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize())
            {
                SkeletonScreen(isLoading = state.loading,
                    skeleton = {
                        ScheduleScreenSkeleton()
                    }
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .pullRefresh(stateRefresh))
                    {
                        itemsIndexed(state.value)
                        {
                                _, item ->
                            Text(text = item.grade.toString() + " " + stringResource(id = R.string.grade),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(horizontal = 32.dp))

                            Box(modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                                .clickable {
                                    clickedItemId = item.id
                                }) {
                                if(clickedItemId == item.id)
                                {
                                    ModalBottomSheet(
                                        schoolSchedule = item,
                                        onClickId = {
                                            clipboardManager.setText(AnnotatedString((item.task.id)))
                                            toastIdCopied.show()
                                        },
                                        onClickAC = {
                                            clipboardManager.setText(AnnotatedString((item.task.accessCode)))
                                            toastACCopied.show()
                                        },
                                        onClickAction = {
                                            val urlIntent = Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(item.task.link)
                                            )
                                            ctx.startActivity(urlIntent)
                                        }
                                    ) {
                                        clickedItemId = ""
                                    }
                                }
                                Column(modifier = Modifier.padding(4.dp)) {
                                    Text(
                                        modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                                        fontWeight = FontWeight.SemiBold,
                                        text = item.classRoom,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.secondary)
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = item.start.toString(),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold)
                                            Text(text = item.end.toString(),
                                                textAlign = TextAlign.Center,
                                                fontSize = 12.sp,
                                                color = MaterialTheme.colorScheme.secondary)
                                        }
                                        Spacer(modifier = Modifier.width(24.dp))
                                        Column {
                                            Text(text = item.lesson, fontWeight = FontWeight.Bold)
                                            Text(text = stringResource(id = R.string.teacher) +  " " + item.teacher,
                                                fontSize = 12.sp,
                                                color = MaterialTheme.colorScheme.secondary)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(state.error.isNotBlank()) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.Center)
                        )
                    }
                    PullRefreshIndicator(isRefreshing, stateRefresh, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }
}