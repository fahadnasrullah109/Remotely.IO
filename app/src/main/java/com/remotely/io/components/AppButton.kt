package com.remotely.io.components

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remotely.io.theme.RemotelyIOTheme
import com.remotely.io.theme.soraFamily

@Composable
fun CoffeeButton(
    modifier: Modifier = Modifier, buttonTitle: String, onTap: () -> Unit, isEnabled: Boolean = true
) {
    Button(
        enabled = isEnabled,
        onClick = onTap,
        modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Text(
            text = buttonTitle,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontFamily = soraFamily
            ),
        )
    }
}

@Preview
@Composable
private fun CoffeeButtonPreview() {
    RemotelyIOTheme {
        CoffeeButton(
            buttonTitle = "GetStarted",
            onTap = {},
        )
    }
}