package com.elnfach.arthouse.presentation.profile.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.components.dataclass.ProfileItem

@Composable
fun CategoryButton(profileItem: ProfileItem)
{
    val typography = MaterialTheme.typography
    var isClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .clickable {
                isClicked = true
            }
    ) {
        if(isClicked)
        {
            isClicked = false
            profileItem.onClick()
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
        ) {
            if(profileItem.image != null)
                Icon(painter = profileItem.image, contentDescription = profileItem.contentDescriptionImage)
            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
            Text(text = profileItem.title)
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 6.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24), contentDescription = null)
            }
        }
    }
}