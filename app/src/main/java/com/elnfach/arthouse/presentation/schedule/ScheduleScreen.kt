package com.elnfach.arthouse.presentation.schedule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.schedule.components.ModalBottomSheet
import com.elnfach.arthouse.presentation.utils.navigation.Router
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    externalRouter: Router,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: ScheduleScreenViewModel = koinViewModel()
) {
    val state = viewModel.state.value
    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    viewModel.selectedDate.observe(lifecycleOwner) {
        if (it != null) {
            selectedDate.value = LocalDate.of(it.year, it.monthNumber, it.dayOfMonth)
        }
    }
    val calendarState = rememberUseCaseState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            viewModel.saveDate(
                com.elnfach.arthouse.model.LocalDate(
                    newDate.year,
                    newDate.monthValue,
                    newDate.dayOfMonth
                )
            )
            viewModel.loadSchoolSchedule()
        }
    )

    val ctx = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var clickedItemId by remember { mutableStateOf("") }
    val toastIdCopied = Toast.makeText(context, stringResource(id = R.string.id_copied), Toast.LENGTH_SHORT)
    val toastACCopied = Toast.makeText(context, stringResource(id = R.string.access_code_copied), Toast.LENGTH_SHORT)
    Scaffold(
        topBar = { TopAppBar(
            title = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.schedule))
                }
            },
            actions = {
                IconButton(
                    onClick = { calendarState.show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_calendar_month_24), contentDescription = null)
                }
            }
        )
        }
    ) {
        LazyColumn(Modifier.padding(it))
        {
            itemsIndexed(state.schedule)
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
                        Row(modifier = Modifier.padding(12.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = item.start,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold)
                                Text(text = item.end,
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
        Box(modifier = Modifier.fillMaxSize()) {
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
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}