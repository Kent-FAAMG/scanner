package com.bureau.qrscanner.sdk.utils;

/**
 * Utility class for parsing Aadhaar QR code data
 * Aadhaar QR codes contain XML data with personal information
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0004J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/AadhaarQrParser;", "", "()V", "TAG", "", "formatAddress", "aadhaarData", "Lcom/bureau/qrscanner/sdk/utils/AadhaarQrParser$AadhaarData;", "isAadhaarQr", "", "qrData", "isValidAadhaarUid", "uid", "maskAadhaarUid", "parseAadhaarQr", "parseBase64Format", "parseDelimitedFormat", "parseXmlFormat", "AadhaarData", "sdk-qrscanner_debug"})
public final class AadhaarQrParser {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AadhaarQrParser";
    @org.jetbrains.annotations.NotNull()
    public static final com.bureau.qrscanner.sdk.utils.AadhaarQrParser INSTANCE = null;
    
    private AadhaarQrParser() {
        super();
    }
    
    /**
     * Parse Aadhaar QR code data from raw QR string
     * @param qrData Raw QR code data
     * @return AadhaarData object with parsed information
     */
    @org.jetbrains.annotations.Nullable()
    public final com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData parseAadhaarQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData) {
        return null;
    }
    
    /**
     * Parse XML format Aadhaar QR
     */
    private final com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData parseXmlFormat(java.lang.String qrData) {
        return null;
    }
    
    /**
     * Parse Base64 encoded Aadhaar QR (common format)
     */
    private final com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData parseBase64Format(java.lang.String qrData) {
        return null;
    }
    
    /**
     * Parse delimited format Aadhaar QR (pipe or comma separated)
     */
    private final com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData parseDelimitedFormat(java.lang.String qrData) {
        return null;
    }
    
    /**
     * Check if the QR code data is from an Aadhaar card
     * @param qrData Raw QR code data
     * @return true if it's Aadhaar QR code
     */
    public final boolean isAadhaarQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData) {
        return false;
    }
    
    /**
     * Mask Aadhaar UID for privacy (show only last 4 digits)
     * @param uid Full Aadhaar UID
     * @return Masked UID (XXXX XXXX 1234)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String maskAadhaarUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    /**
     * Validate Aadhaar UID format
     * @param uid Aadhaar UID to validate
     * @return true if valid format
     */
    public final boolean isValidAadhaarUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return false;
    }
    
    /**
     * Format address from Aadhaar components
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatAddress(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData aadhaarData) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\'\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u0081\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011\u00a8\u00060"}, d2 = {"Lcom/bureau/qrscanner/sdk/utils/AadhaarQrParser$AadhaarData;", "", "uid", "", "name", "gender", "dob", "address", "mobile", "email", "pincode", "state", "district", "subDistrict", "vtc", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getDistrict", "getDob", "getEmail", "getGender", "getMobile", "getName", "getPincode", "getState", "getSubDistrict", "getUid", "getVtc", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "sdk-qrscanner_debug"})
    public static final class AadhaarData {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String uid = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String gender = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String dob = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String address = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String mobile = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String email = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String pincode = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String state = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String district = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String subDistrict = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String vtc = null;
        
        public AadhaarData(@org.jetbrains.annotations.NotNull()
        java.lang.String uid, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String gender, @org.jetbrains.annotations.NotNull()
        java.lang.String dob, @org.jetbrains.annotations.NotNull()
        java.lang.String address, @org.jetbrains.annotations.NotNull()
        java.lang.String mobile, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        java.lang.String pincode, @org.jetbrains.annotations.NotNull()
        java.lang.String state, @org.jetbrains.annotations.NotNull()
        java.lang.String district, @org.jetbrains.annotations.NotNull()
        java.lang.String subDistrict, @org.jetbrains.annotations.NotNull()
        java.lang.String vtc) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUid() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getGender() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDob() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAddress() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMobile() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPincode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDistrict() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSubDistrict() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getVtc() {
            return null;
        }
        
        public AadhaarData() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component12() {
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
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
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
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.bureau.qrscanner.sdk.utils.AadhaarQrParser.AadhaarData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String uid, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String gender, @org.jetbrains.annotations.NotNull()
        java.lang.String dob, @org.jetbrains.annotations.NotNull()
        java.lang.String address, @org.jetbrains.annotations.NotNull()
        java.lang.String mobile, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        java.lang.String pincode, @org.jetbrains.annotations.NotNull()
        java.lang.String state, @org.jetbrains.annotations.NotNull()
        java.lang.String district, @org.jetbrains.annotations.NotNull()
        java.lang.String subDistrict, @org.jetbrains.annotations.NotNull()
        java.lang.String vtc) {
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