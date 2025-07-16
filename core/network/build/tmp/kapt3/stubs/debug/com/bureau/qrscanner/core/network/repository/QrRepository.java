package com.bureau.qrscanner.core.network.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ*\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ*\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/bureau/qrscanner/core/network/repository/QrRepository;", "", "submitAadhaarQr", "Lcom/bureau/qrscanner/core/network/repository/QrSubmissionResult;", "qrData", "", "consent", "", "consentText", "(Ljava/lang/String;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitPanQr", "submitVoterQr", "network_debug"})
public abstract interface QrRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitAadhaarQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitPanQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitVoterQr(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData, boolean consent, @org.jetbrains.annotations.NotNull()
    java.lang.String consentText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bureau.qrscanner.core.network.repository.QrSubmissionResult> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}