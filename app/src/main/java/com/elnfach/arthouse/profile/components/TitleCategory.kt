package com.elnfach.arthouse.profile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.profile.components.dataclass.ProfileItem

@Composable
fun TitleCategory(profileItem: ProfileItem)
{
    val typography = MaterialTheme.typography
    Text(modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 12.dp), text = profileItem.title, style = typography.titleLarge, fontWeight = FontWeight.Bold )
}