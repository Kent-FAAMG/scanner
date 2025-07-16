package com.bureau.qrscanner.sdk.utils

import android.graphics.Rect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Utility class for detecting QR code position relative to the scanning frame
 * Follows Single Responsibility Principle - only handles frame position detection
 */
object QrFrameDetector {
    
    /**
     * Data class representing QR code bounds
     */
    data class QrBounds(
        val left: Float,
        val top: Float,
        val right: Float,
        val bottom: Float
    ) {
        val center: Offset get() = Offset((left + right) / 2, (top + bottom) / 2)
        val width: Float get() = right - left
        val height: Float get() = bottom - top
    }
    
    /**
     * Data class representing scanning frame bounds
     */
    data class FrameBounds(
        val left: Float,
        val top: Float,
        val right: Float,
        val bottom: Float
    ) {
        val center: Offset get() = Offset((left + right) / 2, (top + bottom) / 2)
        val width: Float get() = right - left
        val height: Float get() = bottom - top
    }
    
    /**
     * Result of QR frame detection
     */
    data class DetectionResult(
        val isInFrame: Boolean,
        val overlapPercentage: Float,
        val distanceFromCenter: Float,
        val framePosition: FramePosition
    )
    
    /**
     * Enum representing QR position relative to frame
     */
    enum class FramePosition {
        INSIDE,          // QR is completely inside frame
        OVERLAPPING,     // QR partially overlaps frame
        OUTSIDE_LEFT,    // QR is to the left of frame
        OUTSIDE_RIGHT,   // QR is to the right of frame
        OUTSIDE_TOP,     // QR is above frame
        OUTSIDE_BOTTOM,  // QR is below frame
        OUTSIDE_FAR      // QR is far from frame
    }
    
    /**
     * Calculate frame bounds based on canvas size
     * @param canvasSize Canvas dimensions
     * @param frameRatio Frame size ratio (0.0 to 1.0)
     * @return FrameBounds object
     */
    fun calculateFrameBounds(canvasSize: Size, frameRatio: Float = 0.7f): FrameBounds {
        val frameSize = min(canvasSize.width, canvasSize.height) * frameRatio
        val frameLeft = (canvasSize.width - frameSize) / 2
        val frameTop = (canvasSize.height - frameSize) / 2
        val frameRight = frameLeft + frameSize
        val frameBottom = frameTop + frameSize
        
        return FrameBounds(frameLeft, frameTop, frameRight, frameBottom)
    }
    
    /**
     * Convert ML Kit barcode bounds to QrBounds
     * @param barcode ML Kit barcode object
     * @param imageWidth Image width
     * @param imageHeight Image height
     * @param previewWidth Preview width
     * @param previewHeight Preview height
     * @return QrBounds object
     */
    fun convertBarcodeToQrBounds(
        barcode: com.google.mlkit.vision.barcode.common.Barcode,
        imageWidth: Int,
        imageHeight: Int,
        previewWidth: Int,
        previewHeight: Int
    ): QrBounds? {
        val boundingBox = barcode.boundingBox ?: return null
        
        // Scale coordinates from image to preview
        val scaleX = previewWidth.toFloat() / imageWidth.toFloat()
        val scaleY = previewHeight.toFloat() / imageHeight.toFloat()
        
        return QrBounds(
            left = boundingBox.left * scaleX,
            top = boundingBox.top * scaleY,
            right = boundingBox.right * scaleX,
            bottom = boundingBox.bottom * scaleY
        )
    }
    
    /**
     * Detect QR code position relative to scanning frame
     * @param qrBounds QR code bounds
     * @param frameBounds Scanning frame bounds
     * @param minOverlapThreshold Minimum overlap percentage to consider "in frame"
     * @return DetectionResult
     */
    fun detectQrPosition(
        qrBounds: QrBounds,
        frameBounds: FrameBounds,
        minOverlapThreshold: Float = 0.8f
    ): DetectionResult {
        // Calculate overlap area
        val overlapLeft = max(qrBounds.left, frameBounds.left)
        val overlapTop = max(qrBounds.top, frameBounds.top)
        val overlapRight = min(qrBounds.right, frameBounds.right)
        val overlapBottom = min(qrBounds.bottom, frameBounds.bottom)
        
        val hasOverlap = overlapLeft < overlapRight && overlapTop < overlapBottom
        
        val overlapArea = if (hasOverlap) {
            (overlapRight - overlapLeft) * (overlapBottom - overlapTop)
        } else {
            0f
        }
        
        val qrArea = qrBounds.width * qrBounds.height
        val overlapPercentage = if (qrArea > 0) overlapArea / qrArea else 0f
        
        // Calculate distance from center
        val distanceFromCenter = sqrt(
            (qrBounds.center.x - frameBounds.center.x) * (qrBounds.center.x - frameBounds.center.x) +
            (qrBounds.center.y - frameBounds.center.y) * (qrBounds.center.y - frameBounds.center.y)
        )
        
        // Determine frame position
        val framePosition = when {
            overlapPercentage >= minOverlapThreshold -> FramePosition.INSIDE
            hasOverlap -> FramePosition.OVERLAPPING
            qrBounds.right < frameBounds.left -> FramePosition.OUTSIDE_LEFT
            qrBounds.left > frameBounds.right -> FramePosition.OUTSIDE_RIGHT
            qrBounds.bottom < frameBounds.top -> FramePosition.OUTSIDE_TOP
            qrBounds.top > frameBounds.bottom -> FramePosition.OUTSIDE_BOTTOM
            else -> FramePosition.OUTSIDE_FAR
        }
        
        val isInFrame = overlapPercentage >= minOverlapThreshold
        
        return DetectionResult(
            isInFrame = isInFrame,
            overlapPercentage = overlapPercentage,
            distanceFromCenter = distanceFromCenter,
            framePosition = framePosition
        )
    }
    
    /**
     * Get border color based on detection result
     * @param detectionResult Detection result
     * @return Color for border (Red/Green)
     */
    fun getBorderColor(detectionResult: DetectionResult): androidx.compose.ui.graphics.Color {
        return if (detectionResult.isInFrame) {
            androidx.compose.ui.graphics.Color.Green
        } else {
            androidx.compose.ui.graphics.Color.Red
        }
    }
    
    /**
     * Get border alpha based on detection result
     * @param detectionResult Detection result
     * @return Alpha value for border animation
     */
    fun getBorderAlpha(detectionResult: DetectionResult): Float {
        return when (detectionResult.framePosition) {
            FramePosition.INSIDE -> 1.0f
            FramePosition.OVERLAPPING -> 0.8f
            else -> 0.6f
        }
    }
} 