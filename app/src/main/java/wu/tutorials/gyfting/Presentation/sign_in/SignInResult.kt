package wu.tutorials.gyfting.Presentation.sign_in

import android.service.autofill.UserData

data class SignInResult(
    val data:UserDataa?,
    val errorMessage:String?
)

data class UserDataa (
    val userId:String,
    val username:String?,
    val profilePictureUrl:String?
)
