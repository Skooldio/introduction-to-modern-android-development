package com.skooldio.githubviewer.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skooldio.githubviewer.ui.theme.GitHubViewerTheme
import com.skooldio.githubviewer.ui.theme.MaterialColors

@Composable
fun Header(
    title: String,
    leadingIcon: Painter? = null,
    backIcon: Boolean = false,
    onButtonClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialColors.Orange500)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when {
            backIcon -> {
                IconButton(
                    onClick = onButtonClick,
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }

            leadingIcon != null -> {
                Spacer(modifier = Modifier.size(4.dp))
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = leadingIcon,
                    contentDescription = "Leading",
                )
                Spacer(modifier = Modifier.size(8.dp))
            }

            else -> {
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
private fun TitleOnlyHeaderPreview() {
    GitHubViewerTheme {
        Header(title = "Title Preview")
    }
}

@Preview
@Composable
private fun TitleAndButtonHeaderPreview() {
    GitHubViewerTheme {
        Column(modifier = Modifier.background(MaterialColors.White)) {
            Header(
                title = "Title Preview",
                backIcon = true,
            )
        }
    }
}
