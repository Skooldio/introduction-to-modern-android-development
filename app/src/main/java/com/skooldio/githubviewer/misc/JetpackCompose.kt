package com.skooldio.githubviewer.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(widthDp = 300, backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun JetpackCompose() {
    var enabled: Boolean = false

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = enabled, onCheckedChange = { enabled = it })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Counter(enabled = enabled)
    }
}

@Composable
private fun Counter(
    enabled: Boolean,
) {
    var counter: Int = 0

    Row(
        modifier = Modifier
            .background(
                color = Color.Black.copy(alpha = 0.02f),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            text = "Current: $counter",
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = enabled,
            onClick = { counter += 1 },
        ) {
            Text(text = "Add")
        }
    }
}
