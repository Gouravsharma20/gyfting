package wu.tutorials.gyfting.Presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import wu.tutorials.gyfting.Presentation.enterphonenumberui.Phonenumberentery
import wu.tutorials.gyfting.Presentation.enterphonenumberui.phonenumberentery

@Composable
fun SignInScreen(state: SignInState,onSignInClick:()->Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let{ error->Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
        }

    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Phonenumberentery()
        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(
                Color.Blue
            ), shape = RectangleShape
        ) {
            Text(
                text = "Sign_Up",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
    }



}