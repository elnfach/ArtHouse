package com.elnfach.arthouse.presentation.messages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.utils.navigation.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    externalRouter: Router,
    navController: NavController,
    lifecycleOwner: LifecycleOwner
)
{
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.chat),
                        fontSize = 30.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {

        }
    }
}