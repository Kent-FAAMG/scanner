-if class com.bureau.qrscanner.core.network.model.ErrorResponse
-keepnames class com.bureau.qrscanner.core.network.model.ErrorResponse
-if class com.bureau.qrscanner.core.network.model.ErrorResponse
-keep class com.bureau.qrscanner.core.network.model.ErrorResponseJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
-if class com.bureau.qrscanner.core.network.model.ErrorResponse
-keepnames class kotlin.jvm.internal.DefaultConstructorMarker
-if class com.bureau.qrscanner.core.network.model.ErrorResponse
-keepclassmembers class com.bureau.qrscanner.core.network.model.ErrorResponse {
    public synthetic <init>(boolean,java.lang.String,java.lang.Integer,java.lang.String,java.lang.String,int,kotlin.jvm.internal.DefaultConstructorMarker);
}
