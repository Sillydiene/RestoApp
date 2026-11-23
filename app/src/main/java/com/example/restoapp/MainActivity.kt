
package com.example.restoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import com.example.restoapp.ui.theme.RestoAppTheme
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scope = rememberCoroutineScope()

            RestoAppTheme {
                SignUpScreen(

                    onSignUp = { name, email, password ->

                        scope.launch {
                            try {
                                SupabaseClient.client.auth.signUpWith(Email) {
                                    this.email = email
                                    this.password = password

                                    data = buildJsonObject {
                                        put("full_name", name)
                                    }
                                }

                                Toast.makeText(
                                    this@MainActivity,
                                    "Inscription réussie ! Vérifiez votre e-mail.",
                                    Toast.LENGTH_LONG
                                ).show()

                            } catch (e: Exception) {

                                Toast.makeText(
                                    this@MainActivity,
                                    "Erreur : ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },

                    onLoginClick = {
                        Toast.makeText(
                            this@MainActivity,
                            "Page de connexion à venir",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
