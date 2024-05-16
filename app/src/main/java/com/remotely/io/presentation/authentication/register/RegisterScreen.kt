package com.remotely.io.presentation.authentication.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.remotely.io.R
import com.remotely.io.components.AppButton
import com.remotely.io.components.AppErrorText
import com.remotely.io.theme.RemotelyIOTheme
import com.remotely.io.theme.soraFamily
import com.remotely.io.theme.textAppRed
import com.remotely.io.theme.textGrayColor
import com.remotely.io.theme.textTitleColor
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit,
    onLoginTap: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    RegisterScreenContent(modifier = modifier, uiState = uiState, onEmailChanged = { email ->
        viewModel.onEvent(RegisterUIEvents.OnEmailChanged(email = email))
    }, onPasswordChanged = { password ->
        viewModel.onEvent(RegisterUIEvents.OnPasswordChanged(password = password))
    }, onPhoneChanged = { phone ->
        viewModel.onEvent(RegisterUIEvents.OnPhoneChanged(phone = phone))
    }, onRegisterTapped = {
        viewModel.onEvent(
            RegisterUIEvents.OnRegister(
                emptyEmailError = context.getString(R.string.empty_email_error),
                emailError = context.getString(R.string.email_error),
                emptyPhoneError = context.getString(R.string.empty_phone_error),
                phoneError = context.getString(R.string.password_error),
                emptyPasswordError = context.getString(R.string.empty_password_error),
                passwordError = context.getString(R.string.password_error)
            )
        )
    }, onRegisterSuccess = onRegisterSuccess, onLoginTap = onLoginTap
    )
}

@Composable
private fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRegisterSuccess: () -> Unit,
    onRegisterTapped: () -> Unit,
    onLoginTap: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = hostState)
    }) { contentPaddings ->
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        var showPassword by remember { mutableStateOf(value = false) }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPaddings)
                .padding(
                    horizontal = 20.dp, vertical = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            LaunchedEffect(key1 = uiState) {
                if (uiState.registerSuccess) {
                    onRegisterSuccess.invoke()
                }
            }
            LaunchedEffect(key1 = uiState.error) {
                uiState.error?.let { error ->
                    scope.let {
                        it.launch {
                            hostState.showSnackbar(error)
                        }
                    }
                }
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = textTitleColor)) {
                        append(stringResource(id = R.string.label_register_title1))
                    }
                    withStyle(style = SpanStyle(color = textTitleColor)) {
                        append(" ")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(id = R.string.label_register_title2))
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                style = TextStyle(color = textTitleColor, fontSize = 20.sp),
                fontFamily = soraFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                onClick = { }, colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ), border = BorderStroke(width = 1.dp, color = textGrayColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                        contentDescription = null
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.label_register_with_google),
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = textTitleColor, fontSize = 15.sp),
                        fontFamily = soraFamily
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                onClick = { }, colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ), border = BorderStroke(width = 1.dp, color = textGrayColor)
            ) {

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_facebook),
                        contentDescription = null
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.label_register_with_facebook),
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = textTitleColor, fontSize = 15.sp),
                        fontFamily = soraFamily
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.label_or),
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(color = textGrayColor, fontSize = 16.sp),
                fontFamily = soraFamily
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = uiState.email,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Email, contentDescription = null
                    )
                }, onValueChange = {
                    onEmailChanged.invoke(it)
                }, label = {
                    Text(text = stringResource(id = R.string.label_login_email_address))
                }, keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ), modifier = Modifier.fillMaxWidth(), isError = uiState.emailError != null
            )

            uiState.emailError?.let {
                AppErrorText(
                    error = it, modifier = Modifier.fillMaxWidth(), align = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.phone,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Phone, contentDescription = null
                    )
                },
                onValueChange = {
                    onPhoneChanged.invoke(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.label_register_phone_number))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.phoneError != null
            )

            uiState.phoneError?.let {
                AppErrorText(
                    error = it, modifier = Modifier.fillMaxWidth(), align = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = uiState.password,
                singleLine = true, leadingIcon = {
                    Icon(
                        Icons.Filled.Lock, null
                    )
                }, onValueChange = {
                    onPasswordChanged.invoke(it)
                }, label = {
                    Text(text = stringResource(id = R.string.label_login_password))
                }, visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }, trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility, contentDescription = null
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff, contentDescription = null
                            )
                        }
                    }
                }, keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ), modifier = Modifier.fillMaxWidth(), isError = uiState.passwordError != null
            )

            uiState.passwordError?.let {
                AppErrorText(
                    error = it, modifier = Modifier.fillMaxWidth(), align = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                contentAlignment = Alignment.Center
            ) {
                AppButton(
                    modifier = modifier.fillMaxSize(),
                    isEnabled = uiState.loading.not(),
                    buttonTitle = stringResource(id = R.string.label_register),
                    onTap = {
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        onRegisterTapped.invoke()
                    },
                )
                if (uiState.loading) {
                    CircularProgressIndicator()
                }
            }
            Spacer(
                modifier = Modifier.height(50.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.label_register_create_account),
                    style = TextStyle(color = textGrayColor),
                    fontFamily = soraFamily,
                    fontSize = 18.sp
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                TextButton(
                    onClick = onLoginTap
                ) {
                    Text(
                        text = stringResource(id = R.string.label_register_login),
                        style = TextStyle(color = textAppRed),
                        fontSize = 18.sp,
                        fontFamily = soraFamily
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RemotelyIOTheme {
        RegisterScreenContent(modifier = Modifier.fillMaxSize(),
            uiState = RegisterUiState(),
            onEmailChanged = {},
            onPasswordChanged = {},
            onPhoneChanged = {},
            onRegisterTapped = {},
            onRegisterSuccess = {},
            onLoginTap = {})
    }
}