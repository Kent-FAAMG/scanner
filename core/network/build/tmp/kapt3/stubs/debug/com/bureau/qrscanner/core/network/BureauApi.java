package com.bureau.qrscanner.core.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/bureau/qrscanner/core/network/BureauApi;", "", "submitAadhaarQr", "Lretrofit2/Response;", "Lcom/bureau/qrscanner/core/network/model/AadhaarQrResponse;", "request", "Lcom/bureau/qrscanner/core/network/model/AadhaarQrRequest;", "(Lcom/bureau/qrscanner/core/network/model/AadhaarQrRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitPanQr", "submitVoterQr", "network_debug"})
public abstract interface BureauApi {
    
    @retrofit2.http.POST(value = "v2/services/aadhaar-qr")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitAadhaarQr(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.model.AadhaarQrRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.bureau.qrscanner.core.network.model.AadhaarQrResponse>> $completion);
    
    @retrofit2.http.POST(value = "v2/services/pan-qr")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitPanQr(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.model.AadhaarQrRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.bureau.qrscanner.core.network.model.AadhaarQrResponse>> $completion);
    
    @retrofit2.http.POST(value = "v2/services/voter-qr")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitVoterQr(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.model.AadhaarQrRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.bureau.qrscanner.core.network.model.AadhaarQrResponse>> $completion);
}