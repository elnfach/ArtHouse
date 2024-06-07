package com.elnfach.arthouse.profile

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.R
import com.elnfach.arthouse.profile.components.CategoryButton
import com.elnfach.arthouse.profile.components.LargeButton
import com.elnfach.arthouse.profile.components.TextButton
import com.elnfach.arthouse.profile.components.TitleCategory
import com.elnfach.arthouse.profile.components.dataclass.ProfileItem
import com.elnfach.arthouse.profile.components.enums.ProfileItemType
import com.elnfach.arthouse.profile.details.components.profilePreset
import com.elnfach.arthouse.utils.managers.UserManager
import com.elnfach.arthouse.utils.navigation.Router
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    externalRouter: Router,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleOwner: LifecycleOwner,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val typography = MaterialTheme.typography

    val linkToGithub = "https://github.com/elnfach/ArtHouse"
    val ctx = LocalContext.current

    val state = viewModel.user.value
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }

    viewModel.username.observe(lifecycleOwner) {
        if (it != null) {
            title = it
        }
    }

    val profileItems = profilePreset(state = UserManager.status, context = context, externalRouter = externalRouter,
        onSwitching =  listOf(
            ProfileItem(
                type = ProfileItemType.TEXT_BUTTON,
                title = stringResource(id = R.string.sign_out),
                image = null)
            {
                scope.launch {
                    viewModel.signOutUser()
                }
            }
        )
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    if(UserManager.status)
                        Text(text = title,
                            fontSize = 30.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                    else
                        Text(text = stringResource(id = R.string.profile),
                            fontSize = 30.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it))
        {
            LazyColumn {
                items(profileItems)
                {item->
                    when(item.type)
                    {
                        ProfileItemType.NONE            -> TODO             ()
                        ProfileItemType.LARGE_BUTTON    -> LargeButton      (profileItem = item)
                        ProfileItemType.BUTTON          -> CategoryButton   (profileItem = item)
                        ProfileItemType.TEXT_BUTTON     -> TextButton       (profileItem = item)
                        ProfileItemType.TITLE_CATEGORY  -> TitleCategory    (profileItem = item)
                    }
                }
            }
        }
    }
}