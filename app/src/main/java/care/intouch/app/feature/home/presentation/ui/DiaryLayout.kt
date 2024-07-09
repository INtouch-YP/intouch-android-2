package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Mood
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.NoteCards

@Composable
fun DiaryLayout(
    diaryEntryList: List<DiaryEntry>,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit,
    onSwitcherChange: (Int, Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxSize()
            .fillMaxHeight(),
    ) {
        itemsIndexed(diaryEntryList) { index, diaryEntry ->
            var toggleState by rememberSaveable {
                mutableStateOf(diaryEntry.isSharedWithDoctor)
            }
            NoteCards(
                modifier = Modifier.padding(top = 16.dp),
                dateText = diaryEntry.data,
                noteText = diaryEntry.note,
                moodChipsList = diaryEntry.moodList.map { StringVO.Plain(it.nameId) },
                toggleIsChecked = toggleState,
                onClickToggle = {
                    toggleState = !toggleState
                    onSwitcherChange(diaryEntry.id, index, toggleState)
                },
                onClickTrash = { onDeleteButtonClicked(diaryEntry.id, index) })
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 360
)
fun DiaryLayoutPreview() {
    val diaryEntryList = listOf(
        DiaryEntry(
            id = 1,
            data = buildString {
                append("13, jul")
            },
            note = buildString {
                append("Lorem Ipsum dolor sit amet Lorem Ipsum... ")
            },
            moodList = listOf(
                Mood(
                    nameId = buildString {
                        append("Bad")
                    }
                ),
                Mood(
                    nameId = buildString {
                        append("Loneliness")
                    }
                ),
                Mood(
                    nameId = buildString {
                        append("Loneliness")
                    }
                )
            ),
            isSharedWithDoctor = false
        ),
        DiaryEntry(
            id = 1,
            data = buildString {
                append("13, jul")
            },
            note = buildString {
                append("Lorem Ipsum dolor sit amet Lorem Ipsum... ")
            },
            moodList = listOf(
                Mood(
                    nameId = buildString {
                        append("Bad")
                    }
                )
            ),
            isSharedWithDoctor = false
        )
    )
    var onSwitcherChange by rememberSaveable {
        mutableStateOf(false)
    }
    InTouchTheme {
        DiaryLayout(
            diaryEntryList = diaryEntryList,
            onDeleteButtonClicked = { _, _ -> },
            onSwitcherChange = { _, _, _ -> onSwitcherChange = !onSwitcherChange }
        )
    }

}