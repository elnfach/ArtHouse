package com.elnfach.arthouse.profile.details

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.elnfach.arthouse.BuildConfig
import com.elnfach.arthouse.R
import com.elnfach.arthouse.profile.ProfileScreenViewModel
import com.elnfach.arthouse.profile.components.enums.ProfileDetail
import com.elnfach.arthouse.profile.details.components.DetailsPageScreenTemplate
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.managers.LanguageManager
import com.elnfach.arthouse.utils.managers.NotificationsManager
import com.elnfach.arthouse.utils.managers.ThemeManager
import com.elnfach.arthouse.utils.navigation.Router
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPageScreen(
    externalRouter: Router,
    context: Context,
    navController: NavHostController,
    profileDetail: String,
    viewModel: ProfileScreenViewModel = hiltViewModel()
)
{
    val typography = MaterialTheme.typography

    val state = viewModel.user.value


    val favorites = viewModel.favorites.value
    val enum =  enumValueOf<ProfileDetail>(profileDetail)

    val (selectedThemeDesignOptionsOption, onThemeDesignOptionSelected) = remember { mutableStateOf(viewModel.appearanceOptions[2]) }
    val (selectedNotificationsOption, onNotificationsOptionSelected) = remember { mutableStateOf(viewModel.notificationsOptions[2]) }
    val (selectedLanguageOptions, onLanguageOptionsSelected) = remember { mutableStateOf(viewModel.languageOptions[2]) }

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val welcome = stringResource(id = R.string.welcome)

    var emailIsBlank by remember { mutableStateOf(false) }
    var emailIsNotValid by remember { mutableStateOf(false) }
    var usernameIsBlank by remember { mutableStateOf(false) }
    var passwordIsBlank by remember { mutableStateOf(false) }
    var repeatPasswordIsBlank by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }
    var isChanged by remember { mutableStateOf(false) }

    val stateConfig = State.Loading(
        stringResource(id = R.string.loading_message),
        ProgressIndicator.Linear()
    )

    if(state.loading)
    {
        StateDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = {  }),
            config = StateConfig(state = stateConfig)
        )
    }

    when(enum)
    {
        ProfileDetail.NONE -> TODO()
        ProfileDetail.SIGN_IN -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.log_in),
            navController = navController,
            content = {
                Column {
                    Image(painter = painterResource(id = R.drawable.background), contentDescription = null)
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 56.dp), horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),text = stringResource(id = R.string.log_in), style = typography.titleLarge, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = emailIsBlank, singleLine = true, label = { Text(text = stringResource(id = R.string.email)) },
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            supportingText = { if(emailIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty)) }, value = email, onValueChange = { email = it })
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = passwordIsBlank, singleLine = true, label = { Text(text = stringResource(id = R.string.password)) },
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            supportingText = { if(passwordIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty)) }, value = password, onValueChange = { password = it })
                        Spacer(modifier = Modifier.padding(6.dp))
                        FilledTonalButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if(email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotBlank())
                                {
                                    viewModel.signInUser(email, password, callback = {
                                        if(it)
                                        {
                                            // Toast.makeText(context, welcome, Toast.LENGTH_LONG).show()
                                            navController.popBackStack()
                                        }
                                    })
                                }
                                emailIsBlank = email.isBlank()
                                passwordIsBlank = password.isBlank()
                        }) {
                            Text(text = stringResource(id = R.string.sign_in))
                        }
                        TextButton(onClick = {
                            navController.popBackStack()
                            externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_UP}")
                        }) {
                            Text(text = stringResource(id = R.string.create_account),color = MaterialTheme.colorScheme.secondary)
                        }
                        /*TextButton(onClick = {

                        }) {
                            Text(text = stringResource(id = R.string.forgot_password), textDecoration = TextDecoration.Underline,color = MaterialTheme.colorScheme.secondary)
                        }*/
                    }
                }
            }
        )
        ProfileDetail.SIGN_UP -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.sign_up),
            navController = navController,
            content = {
                Column {
                    Image(painter = painterResource(id = R.drawable.background), contentDescription = null)
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 56.dp), horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(modifier = Modifier.padding(top = 6.dp, bottom = 3.dp),text = stringResource(id = R.string.sign_up), style = typography.titleLarge, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(3.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = emailIsBlank || emailIsNotValid, singleLine = true, label = { Text(text = stringResource(id = R.string.email)) },
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            supportingText = {
                                if(emailIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty))
                                if(emailIsNotValid) Text(text = stringResource(id = R.string.email_not_valid)) },
                            value = email, onValueChange = { email = it })
                        Spacer(modifier = Modifier.padding(3.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = usernameIsBlank, singleLine = true, label = { Text(text = stringResource(id = R.string.username)) },
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            supportingText = { if(usernameIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty)) }, value = username, onValueChange = { username = it })
                        Spacer(modifier = Modifier.padding(3.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = passwordIsBlank, singleLine = true, label = { Text(text = stringResource(id = R.string.password)) },
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            supportingText = { if(passwordIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty)) }, value = password, onValueChange = { password = it })
                        Spacer(modifier = Modifier.padding(3.dp))
                        TextField(modifier = Modifier.fillMaxWidth(), isError = repeatPasswordIsBlank || passwordMismatch, singleLine = true, label = { Text(text = stringResource(id = R.string.repeat_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                            ),
                            supportingText = {
                                if(repeatPasswordIsBlank) Text(text = stringResource(id = R.string.field_not_be_empty))
                                if(passwordMismatch) Text(text = stringResource(id = R.string.password_mismatch))},
                            value = repeatPassword, onValueChange = { repeatPassword = it })
                        Spacer(modifier = Modifier.padding(3.dp))
                        FilledTonalButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if(
                                    email.isNotBlank() &&
                                    Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                                    username.isNotBlank() &&
                                    password.isNotBlank() &&
                                    repeatPassword.isNotBlank() &&
                                    password == repeatPassword
                                ) {
                                    viewModel.createUser(username, email, password, callback = {
                                        if(it)
                                        {
                                            // Toast.makeText(context, welcome, Toast.LENGTH_LONG).show()
                                            navController.popBackStack()
                                        }
                                    })
                                }
                                emailIsBlank = email.isBlank()
                                emailIsNotValid = (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank())
                                usernameIsBlank = username.isBlank()
                                passwordIsBlank = password.isBlank()
                                repeatPasswordIsBlank = repeatPassword.isBlank()
                                passwordMismatch = (password != repeatPassword && repeatPassword.isNotBlank())
                            }) {
                            Text(text = stringResource(id = R.string.signup))
                        }
                        TextButton(onClick = {
                            navController.popBackStack()
                            externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_IN}")
                        }) {
                            Text(text = stringResource(id = R.string.already_have_account), color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
            }
        )
        ProfileDetail.PROFILE -> {
            DetailsPageScreenTemplate(
                title = stringResource(id = R.string.profile),
                navController = navController) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {

                    }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_add_a_photo_24), contentDescription = null)
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = "Имя пользователя: ${viewModel.user.value.value?.displayName}", style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Ваша почта: ${viewModel.user.value.value?.email}", style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_page_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                    TextButton(onClick = {
                        viewModel.signOutUser()
                        navController.popBackStack()
                    }) {
                        Text(text = stringResource(id = R.string.sign_out))
                    }
                }
            }
        }
        ProfileDetail.ACTIVATE_SUBSCRIPTION -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.activate_subscription),
            navController = navController) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null,
                    modifier = Modifier.scale(2.1F))
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                Text(text = stringResource(id = R.string.empty_page_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
            }
        }
        ProfileDetail.FAVORITE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.favorite),
            navController = navController,
            content = {
                viewModel.getAllFavorites()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Icon(painter = painterResource(id = R.drawable.baseline_bookmark_border_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_favorite_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.APPEARANCE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.appearance),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    // Text(modifier = Modifier.padding(bottom = 16.dp), text = stringResource(id = R.string.choose_theme), style = typography.titleLarge, fontWeight = FontWeight.Bold)
                    viewModel.appearanceOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.theme.name == viewModel.theme.value, onClick = {
                            ThemeManager.artHouseTheme = items.theme
                            viewModel.onThemeChanged(items.theme)
                            onThemeDesignOptionSelected(items)
                        })
                    }
                    }
                }
            }
        )
        ProfileDetail.NOTIFICATIONS -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.notifications),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    viewModel.notificationsOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.notifications.name == viewModel.notifications.value, onClick = {
                            NotificationsManager.notifications = items.notifications
                            viewModel.onNotificationsSettingChanged(items.notifications)
                            onNotificationsOptionSelected(items)
                        })
                    }
                    }
                }
            }
        )
        ProfileDetail.LANGUAGE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.change_language),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    viewModel.languageOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.language.name == viewModel.language.value, onClick = {
                            LanguageManager.language = items.language
                            viewModel.onLanguageChanged(items.language)
                            onLanguageOptionsSelected(items)
                            isChanged = true
                        })
                    }
                    }
                    if(isChanged)
                        Text(text = stringResource(id = R.string.language_changed_hint), style = typography.bodySmall)
                }
            }
        )
        ProfileDetail.SHARE_APP -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.share_app),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Icon(painter = painterResource(id = R.drawable.baseline_share_24), contentDescription = null, modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_page_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.PROMOTIONAL_CODE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.promotional_code),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null, modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_page_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.FEEDBACK -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.feedback),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null, modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_page_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.ABOUT_APP -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.about_app),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Icon(painter = painterResource(id = R.mipmap.ic_launcher_foreground), contentDescription = "Logo", modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.app_name), style = typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = "${stringResource(id = R.string.version)} ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})", style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(text = "${stringResource(id = R.string.updated)} 28.03.2024", style = typography.bodyMedium, fontWeight = FontWeight.Normal)
                }
            }
        )
    }
}