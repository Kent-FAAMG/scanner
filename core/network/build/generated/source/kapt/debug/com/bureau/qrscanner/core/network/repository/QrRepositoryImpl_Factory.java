package com.bureau.qrscanner.core.network.repository;

import com.bureau.qrscanner.core.network.BureauApi;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class QrRepositoryImpl_Factory implements Factory<QrRepositoryImpl> {
  private final Provider<BureauApi> bureauApiProvider;

  private final Provider<Moshi> moshiProvider;

  public QrRepositoryImpl_Factory(Provider<BureauApi> bureauApiProvider,
      Provider<Moshi> moshiProvider) {
    this.bureauApiProvider = bureauApiProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public QrRepositoryImpl get() {
    return newInstance(bureauApiProvider.get(), moshiProvider.get());
  }

  public static QrRepositoryImpl_Factory create(Provider<BureauApi> bureauApiProvider,
      Provider<Moshi> moshiProvider) {
    return new QrRepositoryImpl_Factory(bureauApiProvider, moshiProvider);
  }

  public static QrRepositoryImpl newInstance(BureauApi bureauApi, Moshi moshi) {
    return new QrRepositoryImpl(bureauApi, moshi);
  }
}
