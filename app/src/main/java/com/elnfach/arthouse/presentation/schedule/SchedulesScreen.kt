package com.elnfach.arthouse.presentation.schedule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.R
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
fun SchedulesScreen(
    externalRouter: Router,
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: SchedulesScreenViewModel = koinViewModel()
)
{
    val selectedDates = remember { mutableStateOf<List<LocalDate>>(listOf()) }
    val disabledDates = listOf(
        LocalDate.now().minusDays(7),
        LocalDate.now().minusDays(12),
        LocalDate.now().plusDays(3),
    )

    val ctx = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val text by remember { mutableStateOf("Some text")}
    var isClicked by remember { mutableStateOf(false) }
    val toastIdCopied = Toast.makeText(context, stringResource(id = R.string.id_copied), Toast.LENGTH_SHORT)
    val toastACCopied = Toast.makeText(context, stringResource(id = R.string.access_code_copied), Toast.LENGTH_SHORT)
    Scaffold(
        topBar = { TopAppBar(
            title = {
                Column {
                    Text(text = stringResource(id = R.string.schedule))
                }
        })
        }
    ) {
        LazyColumn(Modifier.padding(it))
        {
            item {
                Text(text = "6 класс",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(horizontal = 32.dp))
            }
            item {
                Box(modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    .clickable {
                        isClicked = true
                    }) {
                    if(isClicked)
                    {
                        ModalBottomSheet(
                            onDismissRequest = { isClicked = false }) {
                            Box(modifier = Modifier.fillMaxSize())
                            {
                                Column {
                                    Column(modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Задание",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.secondary)
                                        Box(modifier = Modifier
                                            .padding(horizontal = 24.dp, vertical = 12.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .fillMaxWidth()
                                            .background(
                                                color = MaterialTheme.colorScheme.onSecondary
                                            ))
                                        {
                                            Column(modifier = Modifier.padding(12.dp)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(text = "Идентификатор: ")
                                                    TextButton(onClick =  {
                                                        clipboardManager.setText(AnnotatedString((text)))
                                                        toastIdCopied.show()
                                                    }) {
                                                        Text("847 0215 0835")
                                                    }
                                                }
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(text = "Код доступа: ")
                                                    TextButton(onClick =  {
                                                        clipboardManager.setText(AnnotatedString((text)))
                                                        toastACCopied.show()
                                                    }) {
                                                        Text("TiR6RN")
                                                    }
                                                }
                                                TextButton(onClick =  {
                                                    val urlIntent = Intent(
                                                        Intent.ACTION_VIEW,
                                                        Uri.parse("https://www.youtube.com/")
                                                    )
                                                    ctx.startActivity(urlIntent)
                                                }) {
                                                    Text("Открыть YouTube")
                                                }
                                            }
                                        }
                                    }
                                    Column(modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "Домашнее задание",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.secondary)
                                    }
                                    LazyColumn {
                                        item {
                                            Box(modifier = Modifier
                                                .padding(horizontal = 24.dp, vertical = 12.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .fillMaxSize()
                                                .background(
                                                    color = MaterialTheme.colorScheme.onSecondary
                                                ))
                                            {
                                                Text(text = "муз. характеристика образа Бориса, развитие линии Самозванца",
                                                    modifier = Modifier.padding(12.dp))
                                            }
                                        }
                                        item {
                                            Box(modifier = Modifier
                                                .padding(horizontal = 24.dp, vertical = 12.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .fillMaxSize()
                                                .background(
                                                    color = MaterialTheme.colorScheme.onSecondary
                                                ))
                                            {
                                                Text(text = "муз. характеристика образа Бориса, развитие линии Самозванца",
                                                    modifier = Modifier.padding(12.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.padding(4.dp)) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "15:40",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold)
                                Text(text = "16:40",
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary)
                            }
                            Spacer(modifier = Modifier.width(24.dp))
                            Column {
                                Text(text = "Сольфеджио", fontWeight = FontWeight.Bold)
                                Text(text = "Преподаватель Иванов.А.А",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                        CalendarDialog(
                            state = rememberUseCaseState(),
                            config = CalendarConfig(
                                yearSelection = true,
                                monthSelection = true,
                                style = CalendarStyle.MONTH,
                                disabledDates = disabledDates
                            ),
                            selection = CalendarSelection.Dates { newDates ->
                                selectedDates.value = newDates
                            }
                        )
                    }
                }
            }
        }
    }
}