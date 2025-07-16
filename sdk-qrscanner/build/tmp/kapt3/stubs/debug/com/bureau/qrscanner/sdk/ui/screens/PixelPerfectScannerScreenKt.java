package com.bureau.qrscanner.sdk.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\u001a$\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a$\u0010\u0005\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u0016\u0010\u0006\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aR\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a,\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0003\u001a\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a$\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u000f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000eH\u0002\u001ad\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u000e2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0004\u0012\u00020\u00010\u000e2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\'H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\b(\u001a4\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\b.\u001a\u0018\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002\u001aV\u00104\u001a\u00020\u0001*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u0002072\u0006\u0010:\u001a\u0002072\u0006\u0010;\u001a\u0002072\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u000207H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010@\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006A"}, d2 = {"PermissionDeniedScreen", "", "onRequestPermission", "Lkotlin/Function0;", "onClose", "PermissionRationaleScreen", "PixelPerfectHelpOverlay", "onDismiss", "PixelPerfectScannerScreen", "primaryColor", "Landroidx/compose/ui/graphics/Color;", "scannerConfig", "Lcom/bureau/qrscanner/sdk/ScannerConfig;", "onQrCodeScanned", "Lkotlin/Function1;", "", "onClosePressed", "onTimeout", "PixelPerfectScannerScreen-3J-VO9M", "(JLcom/bureau/qrscanner/sdk/ScannerConfig;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "PixelPerfectScanningFrame", "isQrDetected", "", "frameDetection", "Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$DetectionResult;", "isAnimationEnabled", "modifier", "Landroidx/compose/ui/Modifier;", "formatTime", "seconds", "", "handleAadhaarQrCode", "rawValue", "processImageProxy", "imageProxy", "Landroidx/camera/core/ImageProxy;", "onQrDetected", "onFrameDetection", "previewSize", "Landroidx/compose/ui/geometry/Size;", "processImageProxy-w72PD0c", "processQrFrameDetection", "barcode", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "imageWidth", "imageHeight", "processQrFrameDetection-rPBEpho", "setupAutoFocus", "camera", "Landroidx/camera/core/Camera;", "previewView", "Landroidx/camera/view/PreviewView;", "drawPixelPerfectCorners", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "frameLeft", "", "frameTop", "frameRight", "frameBottom", "cornerLength", "cornerWidth", "color", "alpha", "drawPixelPerfectCorners-Mcns07U", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFFFFJF)V", "sdk-qrscanner_debug"})
public final class PixelPerfectScannerScreenKt {
    
    @androidx.compose.runtime.Composable()
    private static final void PixelPerfectHelpOverlay(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PixelPerfectScanningFrame(boolean isQrDetected, com.bureau.qrscanner.sdk.utils.QrFrameDetector.DetectionResult frameDetection, boolean isAnimationEnabled, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final void setupAutoFocus(androidx.camera.core.Camera camera, androidx.camera.view.PreviewView previewView) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PermissionRationaleScreen(kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermission, kotlin.jvm.functions.Function0<kotlin.Unit> onClose) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PermissionDeniedScreen(kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermission, kotlin.jvm.functions.Function0<kotlin.Unit> onClose) {
    }
    
    /**
     * Handle Aadhaar QR code scanning with proper parsing and privacy measures
     */
    private static final void handleAadhaarQrCode(java.lang.String rawValue, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQrCodeScanned) {
    }
    
    private static final java.lang.String formatTime(int seconds) {
        return null;
    }
}