package care.intouch.app.feature.authorization.presentation

sealed class AuthorizationUiState {
    data object Loading: AuthorizationUiState()
    data object SetPassword: AuthorizationUiState()
    data object Authorized: AuthorizationUiState()
}