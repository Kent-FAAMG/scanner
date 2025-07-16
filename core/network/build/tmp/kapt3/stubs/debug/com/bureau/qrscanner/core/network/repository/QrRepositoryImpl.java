package com.bureau.qrscanner.core.network.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0011J&\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0011J&\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/bureau/qrscanner/core/network/repository/QrRepositoryImpl;", "Lcom/bureau/qrscanner/core/network/repository/QrRepository;", "bureauApi", "Lcom/bureau/qrscanner/core/network/BureauApi;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/bureau/qrscanner/core/network/BureauApi;Lcom/squareup/moshi/Moshi;)V", "parseErrorResponse", "Lcom/bureau/qrscanner/core/network/model/ErrorResponse;", "errorBody", "", "submitAadhaarQr", "Lcom/bureau/qrscanner/core/network/repository/QrSubmissionResult;", "qrData", "consent", "", "consentText", "(Ljava/lang/String;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitPanQr", "submitVoterQr", "network_debug"})
public final class QrRepositoryImpl implements com.bureau.qrscanner.core.network.repository.QrRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.bureau.qrscanner.core.network.BureauApi bureauApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.squareup.moshi.Moshi moshi = null;
    
    @javax.inject.Inject()
    public QrRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.BureauApi bureauApi, @org.jetbrains.annotations.NotNull()
    com.squareup.moshi.Moshi moshi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object submitAadhaarQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object submitPanQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object submitVoterQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion) {
        return null;
    }
    
    private final com.bureau.qrscanner.core.network.model.ErrorResponse parseErrorResponse(java.lang.String errorBody) {
        return null;
    }
}