package com.elnfach.arthouse.create.newsItem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign

fun TextFieldValue.ofMaxLength(maxLength: Int): TextFieldValue {
    val overLength = text.length - maxLength
    return if (overLength > 0) {
        val headIndex = selection.end - overLength
        val trailIndex = selection.end
        if (headIndex >= 0) {
            copy(
                text = text.substring(0, headIndex) + text.substring(trailIndex, text.length),
                selection = TextRange(headIndex)
            )
        } else {
            copy(text.take(maxLength), selection = TextRange(maxLength))
        }
    } else {
        this
    }
}

@Composable
fun TextFieldTemplate(
    maxLength: Int,
    label: String,
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit
)
{
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = { Text(text = label) },
        onValueChange = { setValue(it.ofMaxLength(maxLength)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        supportingText = {
            Text(
                text = "${value.text.length} / $maxLength",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        colors = TextFieldDefaults.colors(
            errorContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}