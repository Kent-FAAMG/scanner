package com.bureau.qrscanner.sdk;

/**
 * Branding configuration for white-label customization
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B;\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JD\u0010\u001b\u001a\u00020\u00002\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u0006H\u0016J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00d6\u0001J\t\u0010#\u001a\u00020\nH\u00d6\u0001J\u0018\u0010$\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0006H\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014\u00a8\u0006("}, d2 = {"Lcom/bureau/qrscanner/sdk/BrandingConfig;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "primaryColor", "", "secondaryColor", "fontRes", "logoUrl", "", "organizationName", "(IILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getFontRes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLogoUrl", "()Ljava/lang/String;", "getOrganizationName", "getPrimaryColor", "()I", "getSecondaryColor", "component1", "component2", "component3", "component4", "component5", "copy", "(IILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/bureau/qrscanner/sdk/BrandingConfig;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "sdk-qrscanner_debug"})
public final class BrandingConfig implements android.os.Parcelable {
    private final int primaryColor = 0;
    private final int secondaryColor = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer fontRes = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String logoUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String organizationName = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.bureau.qrscanner.sdk.BrandingConfig.CREATOR CREATOR = null;
    
    public BrandingConfig(@androidx.annotation.ColorInt()
    int primaryColor, @androidx.annotation.ColorInt()
    int secondaryColor, @androidx.annotation.FontRes()
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer fontRes, @org.jetbrains.annotations.Nullable()
    java.lang.String logoUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String organizationName) {
        super();
    }
    
    public final int getPrimaryColor() {
        return 0;
    }
    
    public final int getSecondaryColor() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getFontRes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLogoUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOrganizationName() {
        return null;
    }
    
    public BrandingConfig(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel) {
        super();
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.bureau.qrscanner.sdk.BrandingConfig copy(@androidx.annotation.ColorInt()
    int primaryColor, @androidx.annotation.ColorInt()
    int secondaryColor, @androidx.annotation.FontRes()
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer fontRes, @org.jetbrains.annotations.Nullable()
    java.lang.String logoUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String organizationName) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/bureau/qrscanner/sdk/BrandingConfig$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/bureau/qrscanner/sdk/BrandingConfig;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/bureau/qrscanner/sdk/BrandingConfig;", "sdk-qrscanner_debug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<com.bureau.qrscanner.sdk.BrandingConfig> {
        
        private CREATOR() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.bureau.qrscanner.sdk.BrandingConfig createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.bureau.qrscanner.sdk.BrandingConfig[] newArray(int size) {
            return null;
        }
    }
}