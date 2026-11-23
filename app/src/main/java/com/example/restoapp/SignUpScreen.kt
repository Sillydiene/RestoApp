
package com.example.restoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Orange = Color(0xFFFF783E)
private val Background = Color(0xFFFFF7F2)
private val Dark = Color(0xFF292421)

@Composable
fun SignUpScreen(
    onSignUp: (String, String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Logo RestoApp
        Box(
            modifier = Modifier
                .size(75.dp)
                .background(Orange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "R",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "RestoApp",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Dark
        )

        Text(
            text = "Découvrez les meilleures tables près de vous",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(22.dp)
            ) {
                Text(
                    text = "Créer un compte",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Dark
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Rejoignez RestoApp et trouvez votre prochain restaurant préféré.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(22.dp))

                Text("Nom complet", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(7.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Votre nom complet") },
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text("Adresse e-mail", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(7.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("exemple@email.com") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text("Mot de passe", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(7.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Minimum 8 caractères") },
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Text(
                                if (passwordVisible) "Masquer"
                                else "Afficher",
                                color = Orange
                            )
                        }
                    },
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text("Confirmer le mot de passe", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(7.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Répétez le mot de passe") },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        errorMessage = when {
                            fullName.isBlank() ||
                                    email.isBlank() ||
                                    password.isBlank() ||
                                    confirmPassword.isBlank() ->
                                "Veuillez remplir tous les champs."

                            !email.contains("@") ->
                                "Veuillez saisir une adresse e-mail valide."

                            password.length < 8 ->
                                "Le mot de passe doit contenir au moins 8 caractères."

                            password != confirmPassword ->
                                "Les mots de passe ne correspondent pas."

                            else -> ""
                        }

                        if (errorMessage.isEmpty()) {
                            onSignUp(fullName, email, password)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    ),
                    shape = RoundedCornerShape(13.dp)
                ) {
                    Text(
                        "Créer mon compte",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Vous avez déjà un compte ?",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )

                    TextButton(onClick = onLoginClick) {
                        Text(
                            "Se connecter",
                            color = Orange,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Votre prochaine découverte culinaire commence ici.",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
