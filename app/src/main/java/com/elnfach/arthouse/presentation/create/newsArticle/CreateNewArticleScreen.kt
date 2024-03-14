package com.elnfach.arthouse.presentation.create.newsArticle

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.foryou.components.NewsArticleCardTop
import com.elnfach.arthouse.presentation.profile.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.profile.signed_in.SignedInScreen
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewArticle(
    router: Router?,
    navController: NavHostController
)
{
    val typography = MaterialTheme.typography
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.create_new_article))
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                navController.popBackStack()
                            }) {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            imageVector = Icons.Default.ArrowBack, contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(Modifier.padding(it)) {

        }
    }
}