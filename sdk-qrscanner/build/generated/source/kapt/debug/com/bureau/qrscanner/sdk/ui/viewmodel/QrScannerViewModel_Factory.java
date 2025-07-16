package com.bureau.qrscanner.sdk.ui.viewmodel;

import com.bureau.qrscanner.core.network.repository.QrRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class QrScannerViewModel_Factory implements Factory<QrScannerViewModel> {
  private final Provider<QrRepository> qrRepositoryProvider;

  public QrScannerViewModel_Factory(Provider<QrRepository> qrRepositoryProvider) {
    this.qrRepositoryProvider = qrRepositoryProvider;
  }

  @Override
  public QrScannerViewModel get() {
    return newInstance(qrRepositoryProvider.get());
  }

  public static QrScannerViewModel_Factory create(Provider<QrRepository> qrRepositoryProvider) {
    return new QrScannerViewModel_Factory(qrRepositoryProvider);
  }

  public static QrScannerViewModel newInstance(QrRepository qrRepository) {
    return new QrScannerViewModel(qrRepository);
  }
}
