package com.bureau.qrscanner.sdk.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BureauFooter(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Powered By" text - Inter, 12px, 400 weight, 18px line height, #bcc2d4
        Text(
            text = "Powered By",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFBCC2D4),
            lineHeight = 18.sp
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Bureau logo and text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Bureau logo icon
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = "Bureau",
                tint = Color(0xFFBCC2D4),
                modifier = Modifier.size(14.dp)
            )
            
            Text(
                text = "Bureau",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFBCC2D4),
                lineHeight = 18.sp
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Separator
        Text(
            text = "|",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFBCC2D4)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Europa logo and text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Europa globe icon
            Icon(
                imageVector = Icons.Default.Public,
                contentDescription = "Europa",
                tint = Color(0xFFBCC2D4),
                modifier = Modifier.size(14.dp)
            )
            
            Text(
                text = "Europa",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFBCC2D4),
                lineHeight = 18.sp
            )
        }
    }
}

// Optional: Enhanced version with proper brand colors
@Composable
fun BureauFooterBranded(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Powered By" text
        Text(
            text = "Powered By",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFB3B3B3),
            letterSpacing = 0.5.sp
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Bureau logo and text - with brand color
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Bureau logo icon in brand color
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = "Bureau",
                tint = Color(0xFF4A90E2), // Brand blue
                modifier = Modifier.size(14.dp)
            )
            
            Text(
                text = "Bureau",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4A90E2), // Brand blue
                letterSpacing = 0.4.sp
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Separator
        Text(
            text = "|",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFB3B3B3)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Europa logo and text - with brand color
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Europa globe icon in brand color
            Icon(
                imageVector = Icons.Default.Public,
                contentDescription = "Europa",
                tint = Color(0xFF28A745), // Brand green
                modifier = Modifier.size(14.dp)
            )
            
            Text(
                text = "Europa",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF28A745), // Brand green
                letterSpacing = 0.4.sp
            )
        }
    }
} 