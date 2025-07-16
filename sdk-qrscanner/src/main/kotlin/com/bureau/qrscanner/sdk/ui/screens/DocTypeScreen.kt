package com.bureau.qrscanner.sdk.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.bureau.qrscanner.sdk.DocumentType
import com.bureau.qrscanner.sdk.ui.components.BureauFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocTypeScreen(
    primaryColor: Color,
    onDocumentTypeSelected: (DocumentType) -> Unit,
    onBackPressed: () -> Unit
) {
    // Define document types with exact specifications
    val documentTypes = listOf(
        DocumentTypeItem(
            type = DocumentType.AADHAAR,
            title = "Aadhaar ID",
            description = "",
            icon = Icons.Default.Person,
            color = Color(0xFF4CAF50)
        ),
        DocumentTypeItem(
            type = DocumentType.PAN,
            title = "PAN", 
            description = "",
            icon = Icons.Default.CreditCard,
            color = Color(0xFF2196F3)
        ),
        DocumentTypeItem(
            type = DocumentType.VOTER_ID,
            title = "Voter ID",
            description = "", 
            icon = Icons.Default.AccountBox,
            color = Color(0xFF9C27B0)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header section - exact design specs
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                // QR Scan title - Inter, 24px, 400 weight, 32px line height, #000000
                Text(
                    text = "QR Scan",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    lineHeight = 32.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Subtitle - Inter, 14px, 400 weight, 20px line height, #000000
                Text(
                    text = "Please scan to capture your government IDs QR",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    lineHeight = 20.sp
                )
            }
            
            // Document type selection cards
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(documentTypes) { documentTypeItem ->
                    DocumentTypeCard(
                        documentTypeItem = documentTypeItem,
                        primaryColor = primaryColor,
                        onSelected = { onDocumentTypeSelected(documentTypeItem.type) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Footer
            BureauFooter()
        }
    }
}

@Composable
private fun DocumentTypeCard(
    documentTypeItem: DocumentTypeItem,
    primaryColor: Color,
    onSelected: () -> Unit
) {
    // Simple card design matching the exact specifications
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menu icon (hamburger icon like in design)
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = Color(0xFF9E9E9E),
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Document title - Inter, 14px, 500 weight, 20px line height, #181d27
            Text(
                text = documentTypeItem.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF181D27),
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
            
            // Arrow icon
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Select",
                tint = Color(0xFF007AFF),
                modifier = Modifier.size(24.dp)
            )
        }
        
        // Divider line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE5E5E5))
                .align(Alignment.BottomCenter)
        )
    }
}

private data class DocumentTypeItem(
    val type: DocumentType,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
) 