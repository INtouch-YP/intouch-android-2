package care.intouch.uikit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun Checkbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    text: String,
    colorIcon: Color = InTouchTheme.colors.textGreen,
    colorText: Color = InTouchTheme.colors.textBlue,
    textStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 10.dp)
                .size(24.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = colorIcon.copy(alpha = 0.4f),
                    shape = CircleShape
                )
                .clickable { onClick.invoke() },
            contentAlignment = Alignment.Center,
        ) {
            if(isChecked) {
                Box(
                    modifier = Modifier
                        .size(13.dp)
                        .clip(CircleShape)
                        .background(colorIcon)
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 10.dp, end = 2.dp),
            text = text,
            style = textStyle,
            color = colorText
        )
    }
}

@Preview
@Composable
fun CheckboxPreview() {
    var isChecked by remember { mutableStateOf(true) }
    var isCheckedSecond by remember { mutableStateOf(false) }

    InTouchTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Checkbox(
                isChecked = isChecked,
                text = "Answer 1"
            ) {
                isChecked = !isChecked
            }

            Checkbox(
                isChecked = isCheckedSecond,
                text = "Answer 2"
            ) {
                isCheckedSecond = !isCheckedSecond
            }
        }
    }
}