package com.ideaapp.presentation.screens.note.secure

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidBiometricAuthenticator
@Inject
constructor(
    @ApplicationContext private val context: Context
) : BiometricPrompt.AuthenticationCallback(),
    BiometricAuthenticator {

    private val executor = ContextCompat.getMainExecutor(context)
    private val biometricManager = BiometricManager.from(context)

    private var onAuthListener: (result: AuthenticationResult) -> Unit = {}
    override fun setOnAuthListener(onAuth: (result: AuthenticationResult) -> Unit) {
        onAuthListener = onAuth
    }

    private var onBiometricStatusListener: (status: DeviceBiometricStatus) -> Unit = {}
    override fun setOnBiometricStatusListener(onStatus: (status: DeviceBiometricStatus) -> Unit) {
        onBiometricStatusListener = onStatus
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        onAuthListener.invoke(AuthenticationResult.Error)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        onAuthListener.invoke(AuthenticationResult.Failed)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        onAuthListener.invoke(AuthenticationResult.Success)
    }

    override fun authenticate(activity: Context) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Secure Screen")
            .setNegativeButtonText("Cancel")
            .build()
        val biometricPrompt = BiometricPrompt(activity as FragmentActivity, executor, this)

        biometricPrompt.authenticate(promptInfo)
    }

    override fun deviceBiometricSupportStatus(): DeviceBiometricStatus {
        return when (biometricManager.canAuthenticate(AUTHENTICATORS)) {
            BiometricManager.BIOMETRIC_SUCCESS -> DeviceBiometricStatus.Success

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> DeviceBiometricStatus.NoHardware

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                DeviceBiometricStatus.HardwareUnavailable

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> DeviceBiometricStatus.NoneEnrolled

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED ->
                DeviceBiometricStatus.SecurityUpdateRequired

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> DeviceBiometricStatus.Unsupported

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> DeviceBiometricStatus.StatusUnknown

            else -> DeviceBiometricStatus.StatusUnknown
        }
    }

    companion object {

        const val AUTHENTICATORS = BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL
    }
}

interface BiometricAuthenticator {
    fun deviceBiometricSupportStatus(): DeviceBiometricStatus
    fun authenticate(activity: Context)
    fun setOnBiometricStatusListener(onStatus: (status: DeviceBiometricStatus) -> Unit)
    fun setOnAuthListener(onAuth: (result: AuthenticationResult) -> Unit)
}

sealed interface DeviceBiometricStatus {
    data object Success : DeviceBiometricStatus
    data object NoHardware : DeviceBiometricStatus
    data object HardwareUnavailable : DeviceBiometricStatus
    data object NoneEnrolled : DeviceBiometricStatus
    data object SecurityUpdateRequired : DeviceBiometricStatus
    data object Unsupported : DeviceBiometricStatus
    data object StatusUnknown : DeviceBiometricStatus
}

sealed interface AuthenticationResult {
    data object Success : AuthenticationResult
    data object Failed : AuthenticationResult
    data object Error : AuthenticationResult
}