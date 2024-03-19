package com.elnfach.arthouse.presentation.profile

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.components.CategoryButton
import com.elnfach.arthouse.presentation.profile.components.LargeButton
import com.elnfach.arthouse.presentation.profile.components.TitleCategory
import com.elnfach.arthouse.presentation.profile.components.enums.ProfileDetail
import com.elnfach.arthouse.presentation.profile.components.dataclass.ProfileItem
import com.elnfach.arthouse.presentation.profile.components.enums.ProfileItemType
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    externalRouter: Router,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val typography = MaterialTheme.typography

    val linkToGithub = "https://github.com/elnfach/ArtHouse"
    val ctx = LocalContext.current

    val profileItems = listOf(
        ProfileItem(
            type = ProfileItemType.LARGE_BUTTON,
            title = stringResource(id = R.string.login),
            subTitle = stringResource(id = R.string.login_desc),
            imageId = R.drawable.baseline_person_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.LOGIN}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.LARGE_BUTTON,
            title = stringResource(id = R.string.activate_your_subscription),
            subTitle = stringResource(id = R.string.activate_your_subscription_desc),
            imageId = R.drawable.baseline_coffee_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.ACTIVATE_SUBSCRIPTION}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.favorite),
            imageId = R.drawable.outline_bookmarks_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.FAVORITE}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.TITLE_CATEGORY,
            title = stringResource(id = R.string.settings),
            onClick = {

            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.appearance),
            imageId = R.drawable.outline_auto_awesome_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.APPEARANCE}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.notifications),
            imageId = R.drawable.baseline_notifications_none_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.NOTIFICATIONS}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.TITLE_CATEGORY,
            title = stringResource(id = R.string.information),
            onClick = {

            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.share_app),
            imageId = R.drawable.outline_ios_share_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SHARE_APP}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.promotional_code),
            imageId = R.drawable.outline_card_giftcard_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.PROMOTIONAL_CODE}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.feedback),
            imageId = R.drawable.outline_email_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.FEEDBACK}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.about_app),
            imageId = R.drawable.baseline_help_outline_24,
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.ABOUT_APP}")
            }
        ),
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
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
                        ProfileItemType.TITLE_CATEGORY  -> TitleCategory    (profileItem = item)
                    }
                }
            }
        }
    }
}