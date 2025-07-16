package com.bureau.qrscanner.sdk;

/**
 * Main SDK interface for Bureau QR Scanner
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\f"}, d2 = {"Lcom/bureau/qrscanner/sdk/BureauQrSdk;", "", "start", "", "context", "Landroid/app/Activity;", "config", "Lcom/bureau/qrscanner/sdk/BrandingConfig;", "scannerConfig", "Lcom/bureau/qrscanner/sdk/ScannerConfig;", "callback", "Lcom/bureau/qrscanner/sdk/BureauQrCallback;", "sdk-qrscanner_debug"})
public abstract interface BureauQrSdk {
    
    /**
     * Start QR scanning flow
     * @param context Activity context
     * @param config Branding configuration
     * @param scannerConfig Scanner behavior configuration
     * @param callback Result callback
     */
    public abstract void start(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.BrandingConfig config, @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.ScannerConfig scannerConfig, @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.BureauQrCallback callback);
    
    /**
     * Main SDK interface for Bureau QR Scanner
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}