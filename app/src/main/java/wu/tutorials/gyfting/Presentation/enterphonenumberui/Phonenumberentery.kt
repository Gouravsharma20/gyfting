package wu.tutorials.gyfting.Presentation.enterphonenumberui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Phonenumberentery() {
    val testState = remember {
        mutableStateOf(" ")
    }
    TextField(
        value = testState.value,
        onValueChange = {
            testState.value = it
        }, placeholder = {
            Text(
                text = "XXXXXXXXXX"
            )
        }
    )
    Button(
        onClick = {
        /*TODO*/
        },
        modifier = Modifier.size(132.dp)
    ) {
        Text(text = "phone-number")
    }

    Column {
    }

}