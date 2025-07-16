package com.bureau.qrscanner.sdk.utils;

/**
 * Utility class for detecting QR code position relative to the scanning frame
 * Follows Single Responsibility Principle - only handles frame position detection
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004\u001f !\"B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\nJ0\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0015J\u001b\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0015\u00f8\u0001\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006#"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector;", "", "()V", "calculateFrameBounds", "Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FrameBounds;", "canvasSize", "Landroidx/compose/ui/geometry/Size;", "frameRatio", "", "calculateFrameBounds-TmRCtEA", "(JF)Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FrameBounds;", "convertBarcodeToQrBounds", "Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$QrBounds;", "barcode", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "imageWidth", "", "imageHeight", "previewWidth", "previewHeight", "detectQrPosition", "Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$DetectionResult;", "qrBounds", "frameBounds", "minOverlapThreshold", "getBorderAlpha", "detectionResult", "getBorderColor", "Landroidx/compose/ui/graphics/Color;", "getBorderColor-vNxB06k", "(Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$DetectionResult;)J", "DetectionResult", "FrameBounds", "FramePosition", "QrBounds", "sdk-qrscanner_debug"})
public final class QrFrameDetector {
    @org.jetbrains.annotations.NotNull()
    public static final com.bureau.qrscanner.sdk.utils.QrFrameDetector INSTANCE = null;
    
    private QrFrameDetector() {
        super();
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
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.QrBounds convertBarcodeToQrBounds(@org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.barcode.common.Barcode barcode, int imageWidth, int imageHeight, int previewWidth, int previewHeight) {
        return null;
    }
    
    /**
     * Detect QR code position relative to scanning frame
     * @param qrBounds QR code bounds
     * @param frameBounds Scanning frame bounds
     * @param minOverlapThreshold Minimum overlap percentage to consider "in frame"
     * @return DetectionResult
     */
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.DetectionResult detectQrPosition(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.utils.QrFrameDetector.QrBounds qrBounds, @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.utils.QrFrameDetector.FrameBounds frameBounds, float minOverlapThreshold) {
        return null;
    }
    
    /**
     * Get border alpha based on detection result
     * @param detectionResult Detection result
     * @return Alpha value for border animation
     */
    public final float getBorderAlpha(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.utils.QrFrameDetector.DetectionResult detectionResult) {
        return 0.0F;
    }
    
    /**
     * Result of QR frame detection
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\bH\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$DetectionResult;", "", "isInFrame", "", "overlapPercentage", "", "distanceFromCenter", "framePosition", "Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FramePosition;", "(ZFFLcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FramePosition;)V", "getDistanceFromCenter", "()F", "getFramePosition", "()Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FramePosition;", "()Z", "getOverlapPercentage", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "sdk-qrscanner_debug"})
    public static final class DetectionResult {
        private final boolean isInFrame = false;
        private final float overlapPercentage = 0.0F;
        private final float distanceFromCenter = 0.0F;
        @org.jetbrains.annotations.NotNull()
        private final com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition framePosition = null;
        
        public DetectionResult(boolean isInFrame, float overlapPercentage, float distanceFromCenter, @org.jetbrains.annotations.NotNull()
        com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition framePosition) {
            super();
        }
        
        public final boolean isInFrame() {
            return false;
        }
        
        public final float getOverlapPercentage() {
            return 0.0F;
        }
        
        public final float getDistanceFromCenter() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition getFramePosition() {
            return null;
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final float component2() {
            return 0.0F;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.DetectionResult copy(boolean isInFrame, float overlapPercentage, float distanceFromCenter, @org.jetbrains.annotations.NotNull()
        com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition framePosition) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Data class representing scanning frame bounds
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\u00020\u000b8F\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0011\u0010\u0013\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\t\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006!"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FrameBounds;", "", "left", "", "top", "right", "bottom", "(FFFF)V", "getBottom", "()F", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", "height", "getHeight", "getLeft", "getRight", "getTop", "width", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sdk-qrscanner_debug"})
    public static final class FrameBounds {
        private final float left = 0.0F;
        private final float top = 0.0F;
        private final float right = 0.0F;
        private final float bottom = 0.0F;
        
        public FrameBounds(float left, float top, float right, float bottom) {
            super();
        }
        
        public final float getLeft() {
            return 0.0F;
        }
        
        public final float getTop() {
            return 0.0F;
        }
        
        public final float getRight() {
            return 0.0F;
        }
        
        public final float getBottom() {
            return 0.0F;
        }
        
        public final float getWidth() {
            return 0.0F;
        }
        
        public final float getHeight() {
            return 0.0F;
        }
        
        public final float component1() {
            return 0.0F;
        }
        
        public final float component2() {
            return 0.0F;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        public final float component4() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.FrameBounds copy(float left, float top, float right, float bottom) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * Enum representing QR position relative to frame
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$FramePosition;", "", "(Ljava/lang/String;I)V", "INSIDE", "OVERLAPPING", "OUTSIDE_LEFT", "OUTSIDE_RIGHT", "OUTSIDE_TOP", "OUTSIDE_BOTTOM", "OUTSIDE_FAR", "sdk-qrscanner_debug"})
    public static enum FramePosition {
        /*public static final*/ INSIDE /* = new INSIDE() */,
        /*public static final*/ OVERLAPPING /* = new OVERLAPPING() */,
        /*public static final*/ OUTSIDE_LEFT /* = new OUTSIDE_LEFT() */,
        /*public static final*/ OUTSIDE_RIGHT /* = new OUTSIDE_RIGHT() */,
        /*public static final*/ OUTSIDE_TOP /* = new OUTSIDE_TOP() */,
        /*public static final*/ OUTSIDE_BOTTOM /* = new OUTSIDE_BOTTOM() */,
        /*public static final*/ OUTSIDE_FAR /* = new OUTSIDE_FAR() */;
        
        FramePosition() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.bureau.qrscanner.sdk.utils.QrFrameDetector.FramePosition> getEntries() {
            return null;
        }
    }
    
    /**
     * Data class representing QR code bounds
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\u00020\u000b8F\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0011\u0010\u0013\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\t\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006!"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/QrFrameDetector$QrBounds;", "", "left", "", "top", "right", "bottom", "(FFFF)V", "getBottom", "()F", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", "height", "getHeight", "getLeft", "getRight", "getTop", "width", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sdk-qrscanner_debug"})
    public static final class QrBounds {
        private final float left = 0.0F;
        private final float top = 0.0F;
        private final float right = 0.0F;
        private final float bottom = 0.0F;
        
        public QrBounds(float left, float top, float right, float bottom) {
            super();
        }
        
        public final float getLeft() {
            return 0.0F;
        }
        
        public final float getTop() {
            return 0.0F;
        }
        
        public final float getRight() {
            return 0.0F;
        }
        
        public final float getBottom() {
            return 0.0F;
        }
        
        public final float getWidth() {
            return 0.0F;
        }
        
        public final float getHeight() {
            return 0.0F;
        }
        
        public final float component1() {
            return 0.0F;
        }
        
        public final float component2() {
            return 0.0F;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        public final float component4() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.QrFrameDetector.QrBounds copy(float left, float top, float right, float bottom) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}