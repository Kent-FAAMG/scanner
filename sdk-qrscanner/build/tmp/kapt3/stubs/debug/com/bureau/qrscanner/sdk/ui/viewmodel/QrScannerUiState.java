package com.bureau.qrscanner.sdk.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u000fH\u00c6\u0003J\t\u0010&\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\u0007H\u00c6\u0003J\t\u0010,\u001a\u00020\u0007H\u00c6\u0003J\t\u0010-\u001a\u00020\u000fH\u00c6\u0003J\t\u0010.\u001a\u00020\u000fH\u00c6\u0003J\u007f\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000fH\u00c6\u0001J\u0013\u00100\u001a\u00020\u000f2\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u000203H\u00d6\u0001J\t\u00104\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u00a8\u00065"}, d2 = {"Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerUiState;", "", "currentScreen", "Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerScreen;", "selectedDocumentType", "Lcom/bureau/qrscanner/sdk/DocumentType;", "scannedQrData", "", "successData", "Lcom/bureau/qrscanner/core/network/model/AadhaarData;", "error", "Lcom/bureau/qrscanner/sdk/BureauQrError;", "statusTitle", "statusMessage", "shouldExit", "", "shouldExitWithSuccess", "shouldExitWithError", "isSubmitting", "(Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerScreen;Lcom/bureau/qrscanner/sdk/DocumentType;Ljava/lang/String;Lcom/bureau/qrscanner/core/network/model/AadhaarData;Lcom/bureau/qrscanner/sdk/BureauQrError;Ljava/lang/String;Ljava/lang/String;ZZZZ)V", "getCurrentScreen", "()Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerScreen;", "getError", "()Lcom/bureau/qrscanner/sdk/BureauQrError;", "()Z", "getScannedQrData", "()Ljava/lang/String;", "getSelectedDocumentType", "()Lcom/bureau/qrscanner/sdk/DocumentType;", "getShouldExit", "getShouldExitWithError", "getShouldExitWithSuccess", "getStatusMessage", "getStatusTitle", "getSuccessData", "()Lcom/bureau/qrscanner/core/network/model/AadhaarData;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "sdk-qrscanner_debug"})
public final class QrScannerUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen currentScreen = null;
    @org.jetbrains.annotations.Nullable()
    private final com.bureau.qrscanner.sdk.DocumentType selectedDocumentType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String scannedQrData = null;
    @org.jetbrains.annotations.Nullable()
    private final com.bureau.qrscanner.core.network.model.AadhaarData successData = null;
    @org.jetbrains.annotations.Nullable()
    private final com.bureau.qrscanner.sdk.BureauQrError error = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String statusTitle = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String statusMessage = null;
    private final boolean shouldExit = false;
    private final boolean shouldExitWithSuccess = false;
    private final boolean shouldExitWithError = false;
    private final boolean isSubmitting = false;
    
    public QrScannerUiState(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen currentScreen, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.sdk.DocumentType selectedDocumentType, @org.jetbrains.annotations.Nullable()
    java.lang.String scannedQrData, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.core.network.model.AadhaarData successData, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.sdk.BureauQrError error, @org.jetbrains.annotations.NotNull()
    java.lang.String statusTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String statusMessage, boolean shouldExit, boolean shouldExitWithSuccess, boolean shouldExitWithError, boolean isSubmitting) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen getCurrentScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.DocumentType getSelectedDocumentType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getScannedQrData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.core.network.model.AadhaarData getSuccessData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.BureauQrError getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatusTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatusMessage() {
        return null;
    }
    
    public final boolean getShouldExit() {
        return false;
    }
    
    public final boolean getShouldExitWithSuccess() {
        return false;
    }
    
    public final boolean getShouldExitWithError() {
        return false;
    }
    
    public final boolean isSubmitting() {
        return false;
    }
    
    public QrScannerUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.DocumentType component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.core.network.model.AadhaarData component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.BureauQrError component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerUiState copy(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen currentScreen, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.sdk.DocumentType selectedDocumentType, @org.jetbrains.annotations.Nullable()
    java.lang.String scannedQrData, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.core.network.model.AadhaarData successData, @org.jetbrains.annotations.Nullable()
    com.bureau.qrscanner.sdk.BureauQrError error, @org.jetbrains.annotations.NotNull()
    java.lang.String statusTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String statusMessage, boolean shouldExit, boolean shouldExitWithSuccess, boolean shouldExitWithError, boolean isSubmitting) {
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