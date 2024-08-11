package wu.tutorials.gyfting.Presentation.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import wu.tutorials.gyfting.Presentation.sign_in.UserDataa

@Composable
fun ProfileScreen(
    userDataa: UserDataa?,
    onSignOut:()->Unit
) {
    Column(modifier = Modifier.fillMaxSize()
        , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(
            userDataa?.profilePictureUrl!=null
            ) {
            AsyncImage(
                model = userDataa.profilePictureUrl
                , contentDescription = "ProfilePitchure", modifier = Modifier
                .size(150.dp)
                .clip(
                    RectangleShape
                ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (userDataa?.username!=null) {
            Text(
                text = userDataa.username
                , fontSize = 36.sp,
                textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSignOut,
                modifier = Modifier
                    .size(width = 150.dp, height = 50.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            10.dp,
                            5.dp
                        )
                    )
            ) {
                Text(text = "Sign_out")
            }
        }
    }
}