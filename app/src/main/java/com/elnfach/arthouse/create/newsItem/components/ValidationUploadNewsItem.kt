package com.elnfach.arthouse.create.newsItem.components

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.elnfach.arthouse.R

@Composable
fun ValidationUploadNewsItem(
    titleField: String,
    contentField: String,
    imageUri: Uri?,
    context: Context,
    content: () -> Unit,
)
{
    if(titleField.length > 10 && contentField.length > 10 && imageUri != null)
    {
        content()
    }
    else if(titleField.length <= 10)
        Toast.makeText(context, stringResource(id = R.string.short_title_error), Toast.LENGTH_LONG).show()
    else if(contentField.length <= 10)
        Toast.makeText(context, stringResource(id = R.string.short_content_error), Toast.LENGTH_LONG).show()
    else if (imageUri == null)
        Toast.makeText(context, stringResource(id = R.string.image_error), Toast.LENGTH_LONG).show()
}