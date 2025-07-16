package com.bureau.qrscanner.sdk.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable

@Composable
fun rememberHelpOverlayState(
    initialShowOverlay: Boolean = true
): HelpOverlayState {
    return HelpOverlayState(
        showOverlay = rememberSaveable { mutableStateOf(initialShowOverlay) }
    )
}

class HelpOverlayState(
    private val showOverlay: androidx.compose.runtime.MutableState<Boolean>
) {
    var isOverlayVisible: Boolean
        get() = showOverlay.value
        set(value) {
            showOverlay.value = value
        }
    
    var isHelpIconVisible: Boolean by mutableStateOf(false)
    
    fun dismissOverlay() {
        isOverlayVisible = false
        isHelpIconVisible = true
    }
    
    fun showOverlay() {
        isOverlayVisible = true
        isHelpIconVisible = false
    }
    
    fun toggleOverlay() {
        if (isOverlayVisible) {
            dismissOverlay()
        } else {
            showOverlay()
        }
    }
} 