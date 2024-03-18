package com.elnfach.arthouse.presentation.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.profile.signed_in.SignedInScreen
import com.elnfach.arthouse.presentation.schedule.components.ModalBottomSheet
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    externalRouter: Router,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.profile),
                        fontSize = 30.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                /*actions ={
                    IconButton(
                        onClick = { externalRouter.routeTo(Screen.Settings.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = null
                        )
                    }
                }*/
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
        {
            LazyColumn {
                item {
                    Box(modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding()
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        .clickable {

                        })
                    {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.baseline_person_24), contentDescription = null)
                                Column(Modifier.padding(start = 12.dp)) {
                                    Text(text = "Авторизуйтесь", style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                                    Text(text = "Ваши данные не синхронизированы", style = typography.bodySmall)
                                }
                            }
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding()
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        .clickable {

                        })
                    {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.baseline_coffee_24), contentDescription = null)
                                Column(Modifier.padding(start = 12.dp)) {
                                    Text(text = "Активируйте подписку", style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                                    Text(text = "Войдите в аккаунт или активируйте подписку", style = typography.bodySmall)
                                }
                            }
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp)
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_bookmarks_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Избранное")
                        }
                    }
                }
                item {
                    Text(modifier = Modifier.padding(start = 24.dp, bottom = 12.dp), text = "Настройки", style = typography.titleLarge, fontWeight = FontWeight.Bold )
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Внешний вид")
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_edit_notifications_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Уведомления")
                        }
                    }
                }
                item {
                    Text(modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 12.dp), text = "Информация", style = typography.titleLarge, fontWeight = FontWeight.Bold )
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Поделиться приложением")
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_card_giftcard_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Ввести промокод")
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.outline_email_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "Обратная связь")
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            // TODO:
                        }) {
                        Row(
                            Modifier
                                .padding(horizontal = 24.dp)
                                .padding(top = 12.dp, bottom = 12.dp)) {
                            Icon(painter = painterResource(id = R.drawable.baseline_help_outline_24), contentDescription = null)
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Text(text = "О приложении")
                        }
                    }
                }
            }
        }
    }
}