package com.elnfach.arthouse.create.schedule

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.create.newsItem.components.TextFieldTemplate
import com.elnfach.arthouse.utils.navigation.Router
import com.elnfach.model.Schedule
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScheduleScreen(
    externalRouter: Router,
    context: Context,
    navController: NavHostController,
    viewModel: AddNewLessonScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val (nameLesson, setNameLesson) = remember { mutableStateOf(TextFieldValue()) }

    var isReturned by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }
    var isClickedSelectorTime by remember { mutableStateOf(false) }
    var isClickedSelectorDate by remember { mutableStateOf(false) }
    var isClickedSelectorTeachers by remember { mutableStateOf(false) }
    var isClickedSelectorClassRooms by remember { mutableStateOf(false) }
    var isClickedSelectorGrades by remember { mutableStateOf(false) }
    var selectedTeacher by remember { mutableStateOf("") }
    var selectedClassRoom by remember { mutableStateOf("") }
    var selectedGrade by remember { mutableIntStateOf(0) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val selectedTime = remember { mutableStateOf<LocalTime?>(null) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }

    val durationLesson: Long = 40

    val teachers = listOf(
        ListOption(titleText = "Федотова С.А"),
        ListOption(titleText = "Иванова В.С"),
        ListOption(titleText = "Зуев А.В"),
        ListOption(titleText = "Львов В.Д"),
        ListOption(titleText = "Фролова В.М"),
        ListOption(titleText = "Мешков А.В"),
        ListOption(titleText = "Данилова У.Г"),
        ListOption(titleText = "Громова М.Р"),
        ListOption(titleText = "Поляков П.М")
    )

    val classRooms = listOf(
        ListOption(titleText = "Концертный зал"),
        ListOption(titleText = "Кабинет 1"),
        ListOption(titleText = "Кабинет 2"),
        ListOption(titleText = "Кабинет 3"),
        ListOption(titleText = "Кабинет 4"),
        ListOption(titleText = "Кабинет 5"),
        ListOption(titleText = "Кабинет 6"),
        ListOption(titleText = "Кабинет 7"),
        ListOption(titleText = "Кабинет 8"),
        ListOption(titleText = "Кабинет 9"),
        ListOption(titleText = "Кабинет 10"),
        ListOption(titleText = "Кабинет 11"),
        ListOption(titleText = "Кабинет 12"),
    )

    val grades = listOf(
        ListOption(titleText = "1 класс"),
        ListOption(titleText = "2 класс"),
        ListOption(titleText = "3 класс"),
        ListOption(titleText = "4 класс"),
        ListOption(titleText = "5 класс")
    )

    val stateConfig = State.Loading(
        stringResource(id = R.string.loading_message),
        ProgressIndicator.Linear()
    )

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.add_new_lesson),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                actions = {
                    TextButton(onClick = {
                        if(!isReturned)
                        {
                            val date = selectedDate.value ?: LocalDate.now()
                            val time = selectedTime.value ?: LocalTime.now()
                            viewModel.addNewLesson(
                                Schedule(
                                    date = date,
                                    start = time,
                                    end = time.plusMinutes(durationLesson),
                                    status = 0,
                                    classRoom = selectedClassRoom,
                                    lesson = nameLesson.text,
                                    teacher = selectedTeacher,
                                    grade = selectedGrade
                                )
                            ) {
                                if(it)
                                {
                                    Toast.makeText(context, "Занятие успешно добвалена!", Toast.LENGTH_LONG).show()
                                    navController.popBackStack()
                                } else
                                    Toast.makeText(context, "Произошла ошибка при добавлении", Toast.LENGTH_LONG).show()
                            }
                        }
                    }) {
                        Text(text = stringResource(id = R.string.add))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if(!isReturned)
                        {
                            isReturned = true
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(id = R.string.return_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TextFieldTemplate(128, stringResource(id = R.string.enter_title_lesson), nameLesson, setNameLesson)
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.lesson_date))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                        .clickable {
                                            isClicked = true
                                            isClickedSelectorDate = true
                                        }
                                )
                                {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "${selectedDate.value?.dayOfMonth}.${selectedDate.value?.monthValue}.${selectedDate.value?.year}")
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.lesson_start_time))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                        .clickable {
                                            isClicked = true
                                            isClickedSelectorTime = true
                                        }
                                )
                                {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if(selectedTime.value != null)
                                            Text(text = selectedTime.value.toString())
                                        else Text(text = stringResource(id = R.string.non_selected))
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.teacher))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                        .clickable {
                                            isClicked = true
                                            isClickedSelectorTeachers = true
                                        }
                                )
                                {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if(selectedTeacher.isBlank())
                                            Text(text = stringResource(id = R.string.non_selected))
                                        else Text(text = selectedTeacher)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.classroom))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                        .clickable {
                                            isClicked = true
                                            isClickedSelectorClassRooms = true
                                        }
                                )
                                {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if(selectedClassRoom.isBlank())
                                            Text(text = stringResource(id = R.string.non_selected))
                                        else Text(text = selectedClassRoom)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.grade_upper_case))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                        .clickable {
                                            isClicked = true
                                            isClickedSelectorGrades = true
                                        }
                                )
                                {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if(selectedGrade == 0)
                                            Text(text = stringResource(id = R.string.non_selected))
                                        else Text(text = "$selectedGrade ${stringResource(id = R.string.grade)}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(state.loading)
            {
                StateDialog(
                    state = rememberUseCaseState(visible = true, onCloseRequest = {  }),
                    config = StateConfig(state = stateConfig)
                )
            }

            // TODO: Rework
            if(isClicked)
            {
                if(isClickedSelectorDate)
                {
                    CalendarDialog(
                        state = rememberUseCaseState(visible = true, true, onCloseRequest = {
                            isClickedSelectorDate = false
                            isClicked = false
                        }),
                        config = CalendarConfig(
                            yearSelection = true,
                            style = CalendarStyle.WEEK,
                        ),
                        selection = CalendarSelection.Date(
                            selectedDate = selectedDate.value
                        ) { newDate ->
                            selectedDate.value = newDate
                        },
                    )
                }
                if(isClickedSelectorTime)
                {
                    DateTimeDialog(
                        state = rememberUseCaseState(visible = true, onCloseRequest = {
                            isClickedSelectorTime = false
                            isClicked = false
                        }),
                        selection = DateTimeSelection.Time { time ->
                            selectedTime.value = time
                        }
                    )
                }
                if(isClickedSelectorTeachers)
                {
                    ListDialog(state = rememberUseCaseState(visible = true, onCloseRequest = {
                        isClickedSelectorTeachers = false
                        isClicked = false
                    }),
                        selection = ListSelection.Single(
                            options = teachers
                        ) { _, option ->
                            selectedTeacher = option.titleText
                        }
                    )
                }
                if(isClickedSelectorClassRooms)
                {
                    ListDialog(state = rememberUseCaseState(visible = true, onCloseRequest = {
                        isClickedSelectorClassRooms = false
                        isClicked = false
                    }),
                        selection = ListSelection.Single(
                            options = classRooms
                        ) { _, option ->
                            selectedClassRoom = option.titleText
                        }
                    )
                }
                if(isClickedSelectorGrades)
                {
                    ListDialog(state = rememberUseCaseState(visible = true, onCloseRequest = {
                        isClickedSelectorGrades = false
                        isClicked = false
                    }),
                        selection = ListSelection.Single(
                            options = grades
                        ) { index, _ ->
                            selectedGrade = index + 1
                        }
                    )
                }
            }
        }
    }
}