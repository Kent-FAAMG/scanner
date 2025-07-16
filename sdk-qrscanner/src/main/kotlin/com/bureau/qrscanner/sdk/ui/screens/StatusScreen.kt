package com.bureau.qrscanner.sdk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.bureau.qrscanner.sdk.ui.components.BureauFooter

@Composable
fun StatusScreen(
    isSuccess: Boolean,
    title: String,
    message: String? = null,
    primaryColor: Color,
    onRetakePressed: (() -> Unit)? = null,
    onAutoRedirect: () -> Unit
) {
    val autoRedirectDelay = if (isSuccess) 3000L else 5000L
    
    // Auto redirect after delay
    LaunchedEffect(Unit) {
        delay(autoRedirectDelay)
        onAutoRedirect()
    }
    
    // Define colors and icons based on success/error state
    val (backgroundColor, iconColor, icon) = when {
        isSuccess -> Triple(
            Color(0xFF28A745),
            Color(0xFF28A745), 
            Icons.Default.CheckCircle
        )
        else -> Triple(
            Color(0xFFDC3545),
            Color(0xFFDC3545),
            Icons.Default.Warning
        )
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            // Status icon with modern design
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(backgroundColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = if (isSuccess) "Success" else "Error",
                    tint = iconColor,
                    modifier = Modifier.size(60.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Title with better typography
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
            
            // Message with improved styling
            if (message != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Action button for failure with modern design
            if (!isSuccess && onRetakePressed != null) {
                Button(
                    onClick = onRetakePressed,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "Try Again",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Auto redirect info
                Text(
                    text = "Redirecting automatically in ${autoRedirectDelay / 1000} seconds",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            } else if (isSuccess) {
                // Success message with auto-redirect info
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Redirecting in ${autoRedirectDelay / 1000} seconds",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        
        // Footer with Bureau branding
        BureauFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        )
    }
} 