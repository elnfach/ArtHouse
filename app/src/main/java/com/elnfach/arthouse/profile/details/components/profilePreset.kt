package com.elnfach.arthouse.profile.details.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.elnfach.arthouse.R
import com.elnfach.arthouse.profile.components.dataclass.ProfileItem
import com.elnfach.arthouse.profile.components.enums.ProfileDetail
import com.elnfach.arthouse.profile.components.enums.ProfileItemType
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.managers.UserManager
import com.elnfach.arthouse.utils.navigation.Router

@Composable
fun profilePreset(
    state: Boolean,
    context: Context,
    externalRouter: Router,
    onSwitching: List<ProfileItem>
) : List<ProfileItem>
{
    if(state)
    {
        val profilePresetList: MutableList<ProfileItem> = mutableListOf(
            ProfileItem(
                type = ProfileItemType.LARGE_BUTTON,
                title = stringResource(id = R.string.activate_subscription),
                subTitle = stringResource(id = R.string.activate_subscription_desc_second),
                image = painterResource(id = R.drawable.baseline_coffee_24),
                onClick = {
                    if(UserManager.status)
                        externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.ACTIVATE_SUBSCRIPTION}")
                    else
                        externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_IN}")
                }
            )
        )
        profilePresetList.addAll(profilePresetList(context, externalRouter))
        profilePresetList.addAll(onSwitching)
        return profilePresetList
    }
    val profilePresetList: MutableList<ProfileItem> = mutableListOf(
        ProfileItem(
            type = ProfileItemType.LARGE_BUTTON,
            title = stringResource(id = R.string.login),
            subTitle = stringResource(id = R.string.login_desc),
            image = painterResource(id = R.drawable.baseline_person_24),
            onClick = {
                if(UserManager.status)
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.PROFILE}")
                else
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_IN}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.LARGE_BUTTON,
            title = stringResource(id = R.string.activate_subscription),
            subTitle = stringResource(id = R.string.activate_subscription_desc),
            image = painterResource(id = R.drawable.baseline_coffee_24),
            onClick = {
                if(UserManager.status)
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.ACTIVATE_SUBSCRIPTION}")
                else
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_IN}")
            }
        )
    )
    profilePresetList.addAll(profilePresetList(context, externalRouter))
    return profilePresetList
}

@Composable
private fun profilePresetList(
    context: Context,
    externalRouter: Router
) : List<ProfileItem>
{
    return listOf(
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.favorite),
            image = painterResource(id = R.drawable.outline_bookmarks_24),
            onClick = {
                if(UserManager.status)
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.FAVORITE}")
                else
                    externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SIGN_IN}")
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
            image = painterResource(id = R.drawable.outline_auto_awesome_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.APPEARANCE}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.notifications),
            image = painterResource(id = R.drawable.baseline_notifications_none_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.NOTIFICATIONS}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.language),
            image = painterResource(id = R.drawable.outline_language_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.LANGUAGE}")
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
            image = painterResource(id = R.drawable.baseline_share_24),
            onClick = {
                //externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.SHARE_APP}")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://ya.ru/")
                )
                context.startActivity(urlIntent)
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.promotional_code),
            image = painterResource(id = R.drawable.outline_card_giftcard_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.PROMOTIONAL_CODE}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.feedback),
            image = painterResource(id = R.drawable.outline_email_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.FEEDBACK}")
            }
        ),
        ProfileItem(
            type = ProfileItemType.BUTTON,
            title = stringResource(id = R.string.about_app),
            image = painterResource(id = R.drawable.baseline_help_outline_24),
            onClick = {
                externalRouter.routeTo("${Screen.ProfileDetail.route}/${ProfileDetail.ABOUT_APP}")
            }
        ),
    )
}