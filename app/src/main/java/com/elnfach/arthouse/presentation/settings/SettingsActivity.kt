package com.elnfach.arthouse.presentation.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.MainActivity
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme

class SettingsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtHouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .clickable {
                                            val navigate = Intent(this@SettingsActivity, MainActivity::class.java)
                                            startActivity(navigate)
                                        }) {
                                        Icon(
                                            modifier = Modifier.padding(4.dp),
                                            imageVector = Icons.Default.ArrowBack, contentDescription = null)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = stringResource(id = R.string.settings))
                                }
                            })
                        }
                    ) {
                        Box(modifier = Modifier.padding(it))
                        {
                            SettingsScreen(this@SettingsActivity)
                        }
                    }
                }
            }
        }
    }
}