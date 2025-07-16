package com.bureau.qr.host;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u001a\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/bureau/qr/host/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "bureauQrSdk", "Lcom/bureau/qrscanner/sdk/BureauQrSdkImpl;", "MainScreen", "", "getCustomConfig", "Lcom/bureau/qrscanner/sdk/BrandingConfig;", "getDefaultConfig", "getGreenConfig", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "startQrScanner", "config", "scannerConfig", "Lcom/bureau/qrscanner/sdk/ScannerConfig;", "host-app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final com.bureau.qrscanner.sdk.BureauQrSdkImpl bureauQrSdk = null;
    
    public MainActivity() {
        super(0);
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.compose.runtime.Composable()
    public final void MainScreen() {
    }
    
    private final void startQrScanner(com.bureau.qrscanner.sdk.BrandingConfig config, com.bureau.qrscanner.sdk.ScannerConfig scannerConfig) {
    }
    
    private final com.bureau.qrscanner.sdk.BrandingConfig getDefaultConfig() {
        return null;
    }
    
    private final com.bureau.qrscanner.sdk.BrandingConfig getCustomConfig() {
        return null;
    }
    
    private final com.bureau.qrscanner.sdk.BrandingConfig getGreenConfig() {
        return null;
    }
}