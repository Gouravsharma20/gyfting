package wu.tutorials.gyfting.Presentation.enterphonenumberui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Enteringotp() {
    val testState = remember {
        mutableStateOf(" ")
    }
    TextField(
        value = testState.value,
        onValueChange = {
            testState.value = it
        }, placeholder = {
            Text(
                text = "XXXXXX"
            )
        }
    )
    Button(
        onClick = {
            /*TODO*/
        },
        modifier = Modifier.size(132.dp)
    ) {
        Text(text = "Submit")
    }

}