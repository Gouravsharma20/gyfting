package wu.tutorials.gyfting

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import wu.tutorials.gyfting.Presentation.Profile.ProfileScreen
import wu.tutorials.gyfting.Presentation.animatedsplashscreen.MainViewModel
import wu.tutorials.gyfting.Presentation.sign_in.GoogleAuthUiClient
import wu.tutorials.gyfting.Presentation.sign_in.SignInScreen
import wu.tutorials.gyfting.Presentation.sign_in.SignInViewModel
import wu.tutorials.gyfting.ui.theme.GyftingTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext
            , oneTapClient = Identity.getSignInClient(
                applicationContext
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    .4f,.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration=500L
                zoomX.doOnEnd { screen.remove()
                }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    .4f,.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration=500L
                zoomY.doOnEnd { screen.remove()
                }
                zoomX.start()
                zoomY.start()

            }
        }
        enableEdgeToEdge()
        setContent {
            GyftingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                    , color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "signIn" ) {
                        composable("signIn") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            LaunchedEffect(
                                key1 = Unit
                            ) {
                                if (googleAuthUiClient.getSignedInUser() !=null) {
                                    navController.navigate("Profile")
                                }

                            }
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {
                                    result ->
                                    if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.SignInWithIntent(intent = result.data?:return@launch)
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                                }
                            )
                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Welcome " ,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(IntentSenderRequest.Builder(
                                            signInIntentSender?:return@launch
                                        ).build()
                                        )
                                    }
                                }
                            )
                        }
                        composable("Profile") {
                            ProfileScreen(
                                userDataa = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Log_out",Toast.LENGTH_LONG
                                        ).show()
                                        navController.popBackStack()
                                    }
                                }
                            )
                            
                        }
                    }

                }

            }
        }
    }
}


