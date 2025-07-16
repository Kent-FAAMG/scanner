package com.bureau.qrscanner.sdk;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0014\u00a8\u0006\t"}, d2 = {"Lcom/bureau/qrscanner/sdk/QrScannerActivity;", "Landroidx/activity/ComponentActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "sdk-qrscanner_debug"})
public final class QrScannerActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.Nullable()
    private static com.bureau.qrscanner.sdk.BureauQrCallback callback;
    @org.jetbrains.annotations.NotNull()
    public static final com.bureau.qrscanner.sdk.QrScannerActivity.Companion Companion = null;
    
    public QrScannerActivity() {
        super(0);
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/bureau/qrscanner/sdk/QrScannerActivity$Companion;", "", "()V", "callback", "Lcom/bureau/qrscanner/sdk/BureauQrCallback;", "getCallback", "()Lcom/bureau/qrscanner/sdk/BureauQrCallback;", "setCallback", "(Lcom/bureau/qrscanner/sdk/BureauQrCallback;)V", "sdk-qrscanner_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.bureau.qrscanner.sdk.BureauQrCallback getCallback() {
            return null;
        }
        
        public final void setCallback(@org.jetbrains.annotations.Nullable()
        com.bureau.qrscanner.sdk.BureauQrCallback p0) {
        }
    }
}