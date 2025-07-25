package com.bureau.qrscanner.core.network.model;

@com.squareup.moshi.JsonClass(generateAdapter = true)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/bureau/qrscanner/core/network/model/AadhaarQrRequest;", "", "encodedString", "", "imageBase64", "imageUrl", "consent", "", "consentText", "custID", "sessionId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getConsent", "()Z", "getConsentText", "()Ljava/lang/String;", "getCustID", "getEncodedString", "getImageBase64", "getImageUrl", "getSessionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "network_debug"})
public final class AadhaarQrRequest {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String encodedString = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String imageBase64 = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String imageUrl = null;
    private final boolean consent = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String consentText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String custID = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sessionId = null;
    
    public AadhaarQrRequest(@com.squareup.moshi.Json(name = "encodedString")
    @org.jetbrains.annotations.NotNull()
    java.lang.String encodedString, @com.squareup.moshi.Json(name = "imageBase64")
    @org.jetbrains.annotations.NotNull()
    java.lang.String imageBase64, @com.squareup.moshi.Json(name = "imageUrl")
    @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @com.squareup.moshi.Json(name = "consent")
    boolean consent, @com.squareup.moshi.Json(name = "consentText")
    @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @com.squareup.moshi.Json(name = "custID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String custID, @com.squareup.moshi.Json(name = "sessionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEncodedString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageBase64() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    public final boolean getConsent() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConsentText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCustID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSessionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.core.network.model.AadhaarQrRequest copy(@com.squareup.moshi.Json(name = "encodedString")
    @org.jetbrains.annotations.NotNull()
    java.lang.String encodedString, @com.squareup.moshi.Json(name = "imageBase64")
    @org.jetbrains.annotations.NotNull()
    java.lang.String imageBase64, @com.squareup.moshi.Json(name = "imageUrl")
    @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @com.squareup.moshi.Json(name = "consent")
    boolean consent, @com.squareup.moshi.Json(name = "consentText")
    @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @com.squareup.moshi.Json(name = "custID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String custID, @com.squareup.moshi.Json(name = "sessionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
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