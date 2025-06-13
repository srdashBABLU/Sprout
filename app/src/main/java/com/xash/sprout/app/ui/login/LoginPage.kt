package com.xash.sprout.app.ui.login

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true
)

@Composable
fun PlantCareLoginScreen(
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var uiState by remember { mutableStateOf(LoginUiState()) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isLandscape = screenWidth > screenHeight

    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val backgroundOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "backgroundOffset"
    )

    val logoScale by animateFloatAsState(
        targetValue = if (uiState.isLoading) 0.8f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "logoScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF2E7D32).copy(alpha = 0.1f + backgroundOffset * 0.1f),
                        Color(0xFF1B5E20).copy(alpha = 0.2f + backgroundOffset * 0.1f),
                        Color(0xFF0D47A1).copy(alpha = 0.05f)
                    ),
                    radius = 1000f + backgroundOffset * 200f
                )
            )
    ) {
        // Floating plant elements
        FloatingPlantElements(backgroundOffset)

        if (isLandscape) {
            LandscapeLayout(
                uiState = uiState,
                onUiStateChange = { uiState = it },
                onLoginClick = onLoginClick,
                onSignUpClick = onSignUpClick,
                onForgotPasswordClick = onForgotPasswordClick,
                logoScale = logoScale
            )
        } else {
            PortraitLayout(
                uiState = uiState,
                onUiStateChange = { uiState = it },
                onLoginClick = onLoginClick,
                onSignUpClick = onSignUpClick,
                onForgotPasswordClick = onForgotPasswordClick,
                logoScale = logoScale
            )
        }
    }
}

@Composable
private fun FloatingPlantElements(offset: Float) {
    val density = LocalDensity.current

    repeat(6) { index ->
        val xOffset = (50 + index * 60).dp
        val yOffset = (100 + index * 80).dp
        val scale = 0.5f + (offset + index * 0.2f) % 1f * 0.3f

        Box(
            modifier = Modifier
                .offset(
                    x = xOffset + (offset * 20).dp,
                    y = yOffset + (kotlin.math.sin(offset * 2 + index) * 10).dp
                )
                .scale(scale)
                .size(24.dp)
                .background(
                    Color(0xFF4CAF50).copy(alpha = 0.1f),
                    CircleShape
                )
        )
    }
}

@Composable
private fun PortraitLayout(
    uiState: LoginUiState,
    onUiStateChange: (LoginUiState) -> Unit,
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    logoScale: Float
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Logo and Title Section
        LogoSection(scale = logoScale)

        Spacer(modifier = Modifier.height(48.dp))

        // Login Form
        LoginForm(
            uiState = uiState,
            onUiStateChange = onUiStateChange,
            onLoginClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom Section
        BottomSection(
            onSignUpClick = onSignUpClick,
            onForgotPasswordClick = onForgotPasswordClick
        )
    }
}

@Composable
private fun LandscapeLayout(
    uiState: LoginUiState,
    onUiStateChange: (LoginUiState) -> Unit,
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    logoScale: Float
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side - Logo
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoSection(scale = logoScale)
        }

        // Right side - Form
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginForm(
                uiState = uiState,
                onUiStateChange = onUiStateChange,
                onLoginClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            BottomSection(
                onSignUpClick = onSignUpClick,
                onForgotPasswordClick = onForgotPasswordClick
            )
        }
    }
}

@Composable
private fun LogoSection(scale: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Plant Logo
        Box(
            modifier = Modifier
                .size(120.dp)
                .scale(scale)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4CAF50),
                            Color(0xFF2E7D32)
                        )
                    ),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Plant Care Logo",
                modifier = Modifier.size(60.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "PlantCare",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                fontSize = 32.sp
            )
        )

        Text(
            text = "Nurture your green friends",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF558B2F),
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoginForm(
    uiState: LoginUiState,
    onUiStateChange: (LoginUiState) -> Unit,
    onLoginClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Error message
            AnimatedVisibility(
                visible = uiState.errorMessage != null,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = uiState.errorMessage ?: "",
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Email field
            AnimatedTextField(
                value = uiState.email,
                onValueChange = {
                    onUiStateChange(
                        uiState.copy(
                            email = it,
                            isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() || it.isEmpty(),
                            errorMessage = null
                        )
                    )
                },
                label = "Email",
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
                isError = !uiState.isEmailValid
            )

            // Password field
            AnimatedTextField(
                value = uiState.password,
                onValueChange = {
                    onUiStateChange(
                        uiState.copy(
                            password = it,
                            isPasswordValid = it.length >= 6 || it.isEmpty(),
                            errorMessage = null
                        )
                    )
                },
                label = "Password",
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (uiState.isPasswordVisible) Icons.Outlined.Star else Icons.Default.Star,
                onTrailingIconClick = {
                    onUiStateChange(uiState.copy(isPasswordVisible = !uiState.isPasswordVisible))
                },
                visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        if (uiState.email.isNotEmpty() && uiState.password.isNotEmpty()) {
                            onLoginClick(uiState.email, uiState.password)
                        }
                    }
                ),
                isError = !uiState.isPasswordValid,
                modifier = Modifier.focusRequester(passwordFocusRequester)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Login Button
            AnimatedLoginButton(
                onClick = {
                    if (uiState.email.isNotEmpty() && uiState.password.isNotEmpty()) {
                        onUiStateChange(uiState.copy(isLoading = true))
                        onLoginClick(uiState.email, uiState.password)
                    } else {
                        onUiStateChange(uiState.copy(errorMessage = "Please fill in all fields"))
                    }
                },
                isLoading = uiState.isLoading,
                enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty() && uiState.isEmailValid && uiState.isPasswordValid
            )
        }
    }
}

@Composable
private fun AnimatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = if (isError) MaterialTheme.colorScheme.error else Color(0xFF4CAF50)
            )
        },
        trailingIcon = trailingIcon?.let { icon ->
            {
                IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF757575)
                    )
                }
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        isError = isError,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF4CAF50),
            focusedLabelColor = Color(0xFF4CAF50),
            cursorColor = Color(0xFF4CAF50)
        )
    )
}

@Composable
private fun AnimatedLoginButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    enabled: Boolean
) {
    val buttonScale by animateFloatAsState(
        targetValue = if (isLoading) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "buttonScale"
    )

    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(buttonScale),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        AnimatedContent(
            targetState = isLoading,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            label = "buttonContent"
        ) { loading ->
            if (loading) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                    Text("Signing in...")
                }
            } else {
                Text(
                    "Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun BottomSection(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = onForgotPasswordClick) {
            Text(
                "Forgot Password?",
                color = Color(0xFF2E7D32),
                fontSize = 14.sp
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Don't have an account?",
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            TextButton(onClick = onSignUpClick) {
                Text(
                    "Sign Up",
                    color = Color(0xFF4CAF50),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}