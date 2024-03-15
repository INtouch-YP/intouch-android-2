package care.intouch.uikit.ui.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.OneLineTextFieldDefaults.BLANC_STRING
import care.intouch.uikit.ui.utill.StringVO

/**
One line text field with the title.

 * Width by default is 280dp, but you can override it by applying Modifier.width().
 * Height based on text size and padding 16dp from both sides of the text.

 * @param modifier the [Modifier] to be applied to this text field.
 * @param titleText the title text above text field.
 * @param value the input text to be shown in the text field.
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param hint the hint text to be shown in the text field, visible when the text field is empty.
 * @param isError indicates if the text field's current value is in error. If set to true, the
 * border color will be red. If [enabled] set false - [isError] is false (whatever it set here).
 * @param enabled controls the enabled state of this text field. When `false`, this component will
 * not respond to user input, and it will appear visually disabled and disabled to accessibility
 * services, [titleText] change color as well.
 * @param readOnly controls the editable state of the text field. When `true`, the text field cannot
 * be modified. However, a user can focus it and copy text from it. Read-only text fields are
 * usually used to display pre-filled forms that a user cannot edit.
 * !Do not change [readOnly] when text is selected, as this cause crash.
 * Known issue by compose developers, may fix in future versions.
 * @param visualTransformation transforms the visual representation of the input [value]
 * For example, you can use
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.PasswordVisualTransformation] to
 * create a password text field. By default, no visual transformation is applied.
 * @param keyboardOptions software keyboard options that cosntains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param backgroundColor that will be used for this text field background color.
 * InputColor85 by default.
 */

@Composable
fun OneLineTextField(
    modifier: Modifier = Modifier,
    titleText: StringVO,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = BLANC_STRING,
    isError: Boolean,
    enabled: Boolean,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = InTouchTheme.colors.inputColor85,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.width(OneLineTextFieldDefaults.MinWidth)
    ) {

        if (titleText.value().isNotBlank()) {
            Text(
                text = titleText.value(),
                style = InTouchTheme.typography.titleSmallTypography,
                color = if (enabled) {
                    InTouchTheme.colors.textColorGreen
                } else {
                    InTouchTheme.colors.textColorGreen40
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        isError && enabled -> Color.Red
                        isFocused && enabled -> InTouchTheme.colors.accentColorGreen
                        else -> backgroundColor
                    },
                    shape = RoundedCornerShape(12.dp),
                ),
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                interactionSource = interactionSource,
                enabled = enabled,
                readOnly = readOnly,
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(InTouchTheme.colors.textColorGreen),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            maxLines = 1,
                            style = InTouchTheme.typography.bodyRegularTypography.copy(
                                color = InTouchTheme.colors.textColorBlue50
                            )
                        )
                    }
                    innerTextField()
                },
                textStyle = if (enabled) {
                    InTouchTheme.typography.bodyRegularTypography
                } else {
                    InTouchTheme.typography.bodyRegularTypography.copy(
                        color = InTouchTheme.colors.textColorBlue50
                    )
                },
                singleLine = true,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun TextInputPreview() {
    InTouchTheme {
        var text by remember { mutableStateOf("") }
        OneLineTextField(
            titleText = StringVO.Plain("Title text may be very looooong"),
            value = text,
            onValueChange = {
                text = it
            },
            hint = "Hint text",
            isError = false,
            enabled = true,
            modifier = Modifier.padding(45.dp)
        )
    }
}

object OneLineTextFieldDefaults {
    /**
     * The default min width applied to a [OneLineTextField].
     * Note that you can override it by applying Modifier.width directly on [OneLineTextField].
     */
    val MinWidth = 280.dp
    const val BLANC_STRING = ""
}