package com.bureau.qrscanner.sdk.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerViewModel;", "Landroidx/lifecycle/ViewModel;", "qrRepository", "Lcom/bureau/qrscanner/core/network/repository/QrRepository;", "(Lcom/bureau/qrscanner/core/network/repository/QrRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/bureau/qrscanner/sdk/ui/viewmodel/QrScannerUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "markExitHandled", "", "onBackPressed", "onErrorRedirect", "onQrCodeScanned", "qrData", "", "onRetakePressed", "onScanTimeout", "onSuccessRedirect", "selectDocumentType", "documentType", "Lcom/bureau/qrscanner/sdk/DocumentType;", "showError", "error", "Lcom/bureau/qrscanner/sdk/BureauQrError;", "submitQrData", "sdk-qrscanner_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class QrScannerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.bureau.qrscanner.core.network.repository.QrRepository qrRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerUiState> uiState = null;
    
    @javax.inject.Inject()
    public QrScannerViewModel(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.core.network.repository.QrRepository qrRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerUiState> getUiState() {
        return null;
    }
    
    public final void selectDocumentType(@org.jetbrains.annotations.NotNull()
    com.bureau.qrscanner.sdk.DocumentType documentType) {
    }
    
    public final void onQrCodeScanned(@org.jetbrains.annotations.NotNull()
    java.lang.String qrData) {
    }
    
    private final void submitQrData(java.lang.String qrData) {
    }
    
    private final void showError(com.bureau.qrscanner.sdk.BureauQrError error) {
    }
    
    public final void onScanTimeout() {
    }
    
    public final void onRetakePressed() {
    }
    
    public final void onBackPressed() {
    }
    
    public final void onSuccessRedirect() {
    }
    
    public final void onErrorRedirect() {
    }
    
    public final void markExitHandled() {
    }
}