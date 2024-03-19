package com.elnfach.arthouse.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.presentation.profile.components.dataclass.ProfileItem

@Composable
fun LargeButton(profileItem: ProfileItem)
{
    val typography = MaterialTheme.typography
    var isClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 6.dp, bottom = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.onSecondary
            )
            .clickable {
                isClicked = true
            }
    )
    {
        if(isClicked)
        {
            isClicked = false
            profileItem.onClick()
        }
        Column(modifier = Modifier.padding(4.dp)) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(profileItem.image != null)
                    Icon(painter = profileItem.image, contentDescription = profileItem.contentDescriptionImage)
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(text = profileItem.title, style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = profileItem.subTitle, style = typography.bodySmall)
                }
            }
        }
    }
}
