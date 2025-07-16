package com.bureau.qrscanner.sdk;

/**
 * Callback interface for SDK events
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H\u0016\u00a8\u0006\u000b"}, d2 = {"Lcom/bureau/qrscanner/sdk/BureauQrCallback;", "", "onCancelled", "", "onFailure", "error", "Lcom/bureau/qrscanner/sdk/BureauQrError;", "onSuccess", "data", "Lcom/bureau/qrscanner/core/network/model/AadhaarData;", "onTimeout", "sdk-qrscanner_debug"})
public abstract interface BureauQrCallback {
    
    public abstract void onSuccess(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.model.AadhaarData data);
    
    public abstract void onFailure(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.BureauQrError error);
    
    public abstract void onCancelled();
    
    public abstract void onTimeout();
    
    /**
     * Callback interface for SDK events
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        public static void onTimeout(@org.jetbrains.annotations.NotNull()
        com.bureau.qrscanner.sdk.BureauQrCallback $this) {
        }
    }
}